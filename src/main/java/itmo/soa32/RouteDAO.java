package itmo.soa32;

import itmo.soa32.dto.LocationDto;
import itmo.soa32.dto.RouteDto;
import itmo.soa32.dto.RoutePostDto;
import itmo.soa32.entities.Route;

import javax.ejb.Stateless;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.io.FileInputStream;
import java.io.IOException;
import java.security.KeyStore;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.cert.CertificateException;
import java.util.List;

@Stateless
public class RouteDAO {
    private Client getHttpClient() throws KeyStoreException, IOException, CertificateException, NoSuchAlgorithmException {
//        String trustFilename = "C:\\Users\\arina\\Downloads\\my-project\\soa-3-2\\localhost.keystore";
        String trustFilename = "/home/studs/s285575/soa/wildfly-preview-22.0.0.Final/standalone/configuration/localhost.keystore";
//        String trustFilename = "localhost.keystore";
        System.setProperty("javax.net.ssl.trustStore", trustFilename);
        System.setProperty("javax.net.ssl.trustStorePassword", "");
        KeyStore keyStore = KeyStore.getInstance(KeyStore.getDefaultType());
        keyStore.load(new FileInputStream(System.getProperty("javax.net.ssl.trustStore")),
                System.getProperty("javax.net.ssl.trustStorePassword").toCharArray());
        return ClientBuilder
                .newBuilder()
                .trustStore(keyStore).hostnameVerifier((s, sslSession) -> true).build();
    }

    Client client;
    String PORT = "4568";

    public Response findRouteByIds(Integer idFrom, Integer idTo, String order) throws IOException, KeyStoreException, CertificateException, NoSuchAlgorithmException {
        client = getHttpClient();
        String URI = "https://localhost:" + PORT + "/soa-3-1.0-SNAPSHOT/routes?from_id=between:"
                + idFrom + ":" + idFrom + "&to_id=between:" + idTo + ":" + idTo +
                "&sort=" + order;
        List<Route> routes = getHttpClient().target(URI).request(MediaType.APPLICATION_JSON_TYPE).get(new GenericType<List<Route>>(){});
        client.close();
        return Response.ok().entity(routes).build();
    }

    public Response addRouteBetweenLocations(Integer idFrom, Integer idTo, int distance, RouteDto routeDto) throws CertificateException, KeyStoreException, IOException, NoSuchAlgorithmException {
        RoutePostDto routePost = new RoutePostDto();
        routePost.setDistance(distance);
        routePost.setName(routeDto.getName());
        routePost.setCoordinates(routeDto.getCoordinates());
        routePost.setFrom(new LocationDto(idFrom));
        routePost.setTo(new LocationDto(idTo));
        System.out.println(routePost);
        client = getHttpClient();
        String URI = "https://localhost:" + PORT + "/soa-3-1.0-SNAPSHOT/routes";
        Response r = client.target(URI).request(MediaType.APPLICATION_JSON_TYPE).post(Entity.json(routePost), Response.class);
        client.close();
        return r;
    }
}


