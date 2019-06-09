import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class AdjList {

    private int V;
    private int E;
    private ArrayList<Integer>[] adj;

    public AdjList(String pathStr){

        File file = new File(pathStr);

        try(Scanner scanner = new Scanner(file)){

            V = scanner.nextInt();
            if(V < 0) throw new IllegalArgumentException("V must be non-negative");
            adj = (ArrayList<Integer>[]) new ArrayList[V];
            for(int i = 0; i < V; i ++)
                adj[i] = new ArrayList<Integer>();

            E = scanner.nextInt();
            if(E < 0) throw new IllegalArgumentException("E must be non-negative");

            for(int i = 0; i < E; i ++){
                int a = scanner.nextInt();
                validateVertex(a);
                int b = scanner.nextInt();
                validateVertex(b);
                adj[a].add(b);
                adj[b].add(a);
            }
        }
        catch(IOException e){
            e.printStackTrace();
        }
    }

    private void validateVertex(int v){
        if(v < 0 || v >= V)
            throw new IllegalArgumentException("vertex " + v + "is invalid");
    }

    public int V(){
        return V;
    }

    public int E(){
        return E;
    }

    public int degree(int v){
        validateVertex(v);
        return adj[v].size();
    }

    public ArrayList<Integer> adj(int v){
        validateVertex(v);
        return adj[v];
    }

    public boolean isAdj(int v, int w){
        validateVertex(v);
        validateVertex(w);
        for(int e: adj[v])
            if(e == w)
                return true;
        return false;
    }

    @Override
    public String toString(){
        StringBuilder sb = new StringBuilder();

        sb.append(String.format("V = %d, E = %d\n", V, E));
        for(int v = 0; v < V; v ++){
            sb.append(v + " : " );
            for(int w : adj[v])
                sb.append(w + " ");
            sb.append('\n');
        }
        return sb.toString();
    }

    public static void main(String args[]){

        AdjList adjList = new AdjList("g.txt");
        System.out.print(adjList);
    }
}
