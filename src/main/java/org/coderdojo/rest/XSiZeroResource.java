package org.coderdojo.rest;

import org.coderdojo.rest.game.GameLogic;
import org.coderdojo.rest.game.Pozitie;

import javax.inject.Singleton;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Singleton
@Path("xsizero")
public class XSiZeroResource {
    private final Pozitie[] tabla = new Pozitie[9];

    public XSiZeroResource() {
        for (int i = 0; i < 9; i++) {
            tabla[i] = Pozitie.GOL;
        }
    }

    @GET
    @Produces("text/plain")
    public String getBoard() {
        StringBuilder builder = new StringBuilder(9);

        for (int i = 0; i < 9; i++) {
            if (tabla[i] == Pozitie.O) {
                builder.append("O");
            } else if (tabla[i] == Pozitie.X) {
                builder.append("X");
            } else {
                builder.append(" ");
            }
        }

        return builder.toString();
    }

    @POST
    @Consumes(MediaType.TEXT_PLAIN)
    public Response getNextMove(String currentMoveStr) {
        int currentMove = Integer.valueOf(currentMoveStr);
        if (tabla[currentMove] == Pozitie.GOL) {
            tabla[currentMove] = Pozitie.X;

            int nextMove = GameLogic.nextPosition(tabla);

            if (nextMove != -1) {
                tabla[nextMove] = Pozitie.O;
            }

            return Response.ok(nextMove).build();
        }

        return Response.serverError().build();
    }

    @POST
    @Path("reset")
    public Response reset() {
        for (int i = 0; i < 9; i++) {
            tabla[i] = Pozitie.GOL;
        }

        return Response.ok().build();
    }
}
