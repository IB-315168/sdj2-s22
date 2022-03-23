package dk.via.json;

import java.io.IOException;

public class StartClient {
    public static void main(String[] args) throws IOException {
        MathClient client = MathClientImplementation.getInstance("localhost", 8888);
        System.out.println(client.plus(2, 2));
        client.close();
    }
}