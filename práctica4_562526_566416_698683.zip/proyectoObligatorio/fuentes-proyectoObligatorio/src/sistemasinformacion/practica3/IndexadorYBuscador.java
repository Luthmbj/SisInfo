package sistemasinformacion.practica3;

import org.apache.lucene.document.Document;
import org.apache.lucene.index.IndexWriter;
import org.apache.lucene.index.IndexWriterConfig;
import org.apache.lucene.document.TextField;
import org.apache.lucene.document.StringField;
import org.apache.lucene.document.Field;
import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.analysis.core.SimpleAnalyzer;
import org.apache.lucene.analysis.es.SpanishAnalyzer;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.FSDirectory;
import org.apache.lucene.store.RAMDirectory;
import org.apache.lucene.util.Version;
import org.apache.lucene.index.DirectoryReader;
import org.apache.lucene.search.IndexSearcher;
import org.apache.lucene.search.Query;
import org.apache.lucene.queryparser.classic.QueryParser;
import org.apache.lucene.queryparser.classic.ParseException;
import org.apache.lucene.search.ScoreDoc;
import org.apache.lucene.search.TopDocs;


import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.Scanner;
import java.io.IOException;
import java.io.InputStream;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.io.BufferedReader;
import java.io.File;
import java.io.Reader;


public class IndexadorYBuscador{

	private Collection <String> ficherosAIndexar = new ArrayList<String>();
	private Collection <String> queries = new ArrayList <String>();
	//private StandardAnalyzer analizador = new StandardAnalyzer(Version.LUCENE_40);
	//private SimpleAnalyzer analizador = new SimpleAnalyzer(Version.LUCENE_40);
	private SpanishAnalyzer analizador = new SpanishAnalyzer(Version.LUCENE_40);
	//private static String dir = "./ficheros";
	
	
	public IndexadorYBuscador(Collection<String> ficherosAIndexar, Collection<String> queries){
		this.ficherosAIndexar = ficherosAIndexar;
		this.queries = queries;
	}
	
	private void anhadirFichero(IndexWriter indice, String path) 
	throws IOException {
		InputStream inputStream = new FileInputStream(path);
		BufferedReader inputStreamReader = new BufferedReader(
				new InputStreamReader(inputStream,"UTF-8"));
		
		Document doc = new Document();   
		doc.add(new TextField("contenido", inputStreamReader));
		doc.add(new StringField("path", path, Field.Store.YES));
		indice.addDocument(doc);
	}
	
	private Directory crearIndiceEnUnDirectorio(File path) throws IOException{
		IndexWriter indice = null;
		Directory directorioAlmacenarIndice = FSDirectory.open(path);

		IndexWriterConfig configuracionIndice = new IndexWriterConfig(Version.LUCENE_40, analizador);

		indice = new IndexWriter(directorioAlmacenarIndice, configuracionIndice);
		
		Collection <String> paths = this.ficherosAIndexar;
		Iterator <String> iterador = paths.iterator();
		while (iterador.hasNext()){
			String p = (String) iterador.next();
			anhadirFichero(indice, p);			
		}
		indice.close();
		return directorioAlmacenarIndice;
	}
	
	private void buscarQueryEnIndice(Directory directorioDelIndice, int paginas, int hitsPorPagina, String queryAsString)
	throws IOException{

		DirectoryReader directoryReader = DirectoryReader.open(directorioDelIndice);
		IndexSearcher buscador = new IndexSearcher(directoryReader);
		
		QueryParser queryParser = new QueryParser(Version.LUCENE_40, "contenido", analizador); 
		Query query = null;
		try{
			query = queryParser.parse(queryAsString);
			TopDocs resultado = buscador.search(query, paginas * hitsPorPagina);
			ScoreDoc[] hits = resultado.scoreDocs;
		      
			//System.out.println("Found " + hits.length + " hits.");
			System.out.println("Query: "+ queryAsString + ", found " + hits.length + " hits.");
			for(int i = 0; i< hits.length; ++i) {
				int docId = hits[i].doc;
				Document doc = buscador.doc(docId);
				System.out.println((i + 1) + ". " + doc.get("path") + "\t" + hits[i].score);
			}
		}catch (ParseException e){
			throw new IOException(e);
		}	
	}
	
	private void buscarQueries(Directory directorioDelIndice, int paginas, int hitsPorPagina)
	throws IOException{
		Collection <String> queries = this.queries;
		Iterator <String> iterador = queries.iterator();
		while (iterador.hasNext()){
			String queryAsString = (String) iterador.next();
			buscarQueryEnIndice(directorioDelIndice, paginas, hitsPorPagina, queryAsString);			
		}

	}
	
	/*
	 * Método que dada una carpeta, obtiene todos los ficheros ".txt" de ésta de de sus
	 * subcarpetas
	 */
	private static Collection <String> recorrerDirectorio(File c, Collection<String> f){
		Collection<String> ficheros = f;
        String archivo;
        int i = 0;
        // Se comprueba si la carpeta pasada com parámetro es directorio
        if(c.isDirectory()){
        	File[] listaF = c.listFiles(); 
        	while(i < listaF.length) {
                if (listaF[i].isFile()){
                	archivo = listaF[i].getName();
                    if (archivo.endsWith(".txt")){
                    	System.out.println("Fichero encontrado: "+ c +"/" + archivo);
                    	ficheros.add(c + "/" + archivo);
                    }
                }
                if(listaF[i].isDirectory())  ficheros = recorrerDirectorio(listaF[i],ficheros);

                i++;
            }
        }
        else System.out.println(c + " no es un directorio correcto");
		return f;
	}
	
	
	
	public static void main(String[]args) throws IOException{
		Scanner txt = new Scanner(System.in);
		System.out.printf("Introduzca directorio en el buscar: ");
		File carpeta = new File(txt.nextLine());
		Collection <String> ficheros = recorrerDirectorio(carpeta,new ArrayList<String>());
		/*ficheros.add("./ficheros/uno.txt");
		ficheros.add("./ficheros/dos.txt");
		ficheros.add("./ficheros/tres.txt");
		ficheros.add("./ficheros/cuatro.txt");*/
		Collection <String> queries = new ArrayList <String>();	
		System.out.println();
		System.out.printf("Introduzca query: [FIN para salir]");
		String query="";
		while(!(query = txt.nextLine()).equals("FIN")){
			queries.add(query);
			System.out.printf("Introduzca query: [FIN para salir]");
		}
		txt.close();
		IndexadorYBuscador ejemplo = new IndexadorYBuscador(ficheros, queries);
		Directory directorioDelIndiceCreado = ejemplo.crearIndiceEnUnDirectorio(carpeta);

		ejemplo.buscarQueries(directorioDelIndiceCreado, 1, ficheros.size());
	}
}
