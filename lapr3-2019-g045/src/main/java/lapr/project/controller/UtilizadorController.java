package lapr.project.controller;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import lapr.project.model.*;

public class UtilizadorController {

    Transacao t = new Transacao();

    public int addUsers(List<String[]> strings) {

        ArrayList<Utilizador> users = new ArrayList<>();

        for (String[] s : strings) {

            Utilizador utilizador = new Utilizador(s[0], s[1], Float.parseFloat(s[2]), Float.parseFloat(s[3]), Float.parseFloat(s[4]), s[5], s[6], s[7]);

            users.add(utilizador);
        }

        boolean success = t.addAllUsers(users);
        if (success) {
            return strings.size();
        } else {
            return 0;
        }

    }

    public int registerUser(String username, String email, String password, String visaCardNumber, int height, int weight, BigDecimal avgcs, String gender) {
        
        Utilizador user = new Utilizador(username, email, password, visaCardNumber, (float) height, (float) weight, avgcs.floatValue(), gender);
//        user.setUsername(username);
//        user.setEmail(email);
//        user.setPassword(password);
//        user.setCartaoCredito(visaCardNumber);
//        user.setAltura((float) height);
//        user.setPeso((float) weight);
//        user.setGenero(gender);
        if(salvarUtilizador(user)) {
            return 1;
        } else {
            return 0;
        }
        
    }
    
    /*
    
    */
    
    
    
    /**
     * *
     * Tenta salvar o objeto Utilizador criado
     *
     * @param utilizador
     * @return
     */
    public boolean salvarUtilizador(Utilizador utilizador) {
        return utilizador.save();
    }

    public void setTransacao(Transacao t) {
        this.t = t;
    }
}
