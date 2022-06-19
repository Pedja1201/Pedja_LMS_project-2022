package rs.ac.singidunum.wbis.app;

import org.apache.jena.arq.querybuilder.SelectBuilder;
import org.apache.jena.arq.querybuilder.UpdateBuilder;
import org.apache.jena.graph.Node;
import org.apache.jena.graph.NodeFactory;
import org.apache.jena.query.*;
import org.apache.jena.rdf.model.RDFNode;
import org.apache.jena.update.UpdateExecutionFactory;
import org.apache.jena.update.UpdateProcessor;
import org.apache.jena.update.UpdateRequest;

public class PredmetTDB {
    public static void main(String[] args) {

        //Kreiramo triplet koji ubacujemo u RDF store
        Node subject = NodeFactory.createURI("http://www.singidunum.ac.rs/predmet#predmet1");
        Node predicate = NodeFactory.createURI("http://www.w3.org/1999/02/22-rdf-syntax-ns#type");
        Node object = NodeFactory.createURI("http://www.singidunum.ac.rs/predmet#Predmet");
        //Da bismo menjali sadrzaj store-a koristimo UpdateBuilder
        UpdateBuilder ub = new UpdateBuilder()
                //insert komanda za ubacivanje
                .addInsert(subject, predicate, object);

        try {
            //kreiramo request, i �aljemo na remote lokaciju
            UpdateRequest req = ub.buildRequest();
            UpdateProcessor u = UpdateExecutionFactory.createRemote(req, "http://localhost:3030/predmeti");
            u.execute();
        }
        catch(Exception e){
            e.printStackTrace();
        }
        //primer dobavljanja koriste�i string upit
        String query = "SELECT ?subjekat WHERE { ?subjekat  <http://www.w3.org/1999/02/22-rdf-syntax-ns#type>  <http://www.singidunum.ac.rs/predmet#Predmet> }";

        //koristimo sparqlService za slanje zahteva preko http
        QueryExecution q = QueryExecutionFactory
                .sparqlService("http://localhost:3030/predmeti", query);
        //kao odgovor dobijamo ResultSet kroz koji iteriramo
        ResultSet results = q.execSelect();
        while (results.hasNext()) {
            //Stavke iz ResultSet su solution, koji sadrze promenljive koje smo
            //zadali u upitu
            QuerySolution solution = results.nextSolution();
            RDFNode subjekat = solution.get("subjekat");
            System.out.println(subjekat);
        }

        //primer koriste�i select builder uz upotrebu prefiksa
        SelectBuilder sb = new SelectBuilder()
                .setDistinct(true)
                .addPrefix("rdf", "http://www.w3.org/1999/02/22-rdf-syntax-ns#")
                .addPrefix("predmet", "http://www.singidunum.ac.rs/predmet#")
                .addVar("?subjekat")
                .addWhere("?subjekat", "rdf:type", "predmet:Predmet");
        Query q2 = sb.build();
        QueryExecution qe2 = QueryExecutionFactory.sparqlService("http://localhost:3030/predmeti", q2);
        ResultSet rs = qe2.execSelect();
        while (rs.hasNext()) {
            QuerySolution solution = rs.nextSolution();
            RDFNode subjekat = solution.get("subjekat");
            System.out.println(subjekat);
        }


    }
}
