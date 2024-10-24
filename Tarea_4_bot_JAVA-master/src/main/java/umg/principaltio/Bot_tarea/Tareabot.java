package umg.principaltio.Bot_tarea;

import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class Tareabot extends TelegramLongPollingBot {




    double euro = 8.45;

    Calendar calendario = Calendar.getInstance();
    SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
    String fechaHoraActual = formato.format(calendario.getTime());

    @Override
    public String getBotUsername() {
        return "Fabianlobos_bot";
    }

    @Override
    public String getBotToken() {
        return "7441021485:AAFThD97fDHz5WSaKUq_bz6yOtoz9p7bX04";
    }


    @Override
    public void onUpdateReceived(Update update) {

        String nombre = update.getMessage().getFrom().getFirstName();
        String apellido = update.getMessage().getFrom().getLastName();
        String nickname = update.getMessage().getFrom().getUserName();


        if (update.hasMessage() && update.getMessage().hasText()) {
            String message_text = update.getMessage().getText();
            long chat_id = update.getMessage().getChatId();



        if (message_text.toLowerCase().equals("/info")){

            sendText(chat_id,"Mi informacion personal es:");
            sendText(chat_id,"No.Carnet: 0905-20-6300");
            sendText(chat_id,"NOMBRE: Dimas Fabián Jiménez Lobos");
            sendText(chat_id,"SEMESTRE: 4to y 8vo :)");
        }else  if (message_text.toLowerCase().equals("/progra")) {

            sendText(chat_id, "¿Cómo me parece la clase de progra?");
            sendText(chat_id, "Me parece algo complicada pero muy interesante cada dia de clases :)");

        }
        else if (message_text.toLowerCase().equals("/hola")) {

                sendText(chat_id, "Hola "+nickname+ " La fecha de hoy es: "+ fechaHoraActual);
            }
        else if (message_text.toLowerCase().startsWith("/cambio")) {

                String[] parts = message_text.split(" ");
                if (parts.length == 2) {
                    String cantidad = parts[1];
                    double resultado = Integer.parseInt(cantidad) * euro;
                    sendText(chat_id, "La cantidad de "+cantidad+ "€ tiene un valor de: Q"+resultado);
                } else {
                    sendText(chat_id, "Por favor, usa el formato /cambio <cantidad de euros a convertir>");
                }
            }
else if (message_text.toLowerCase().startsWith("/grupal")) {

//            if (chat_id == 1510734672) {
//                String msg= nombre+ "te saluda";
//                sendText(chat_id, "no me dio copia de arqui ");
//            }
//            System.out.println("Enviaste:" +message_text);
     //   }

            List<Long> groupIds = new ArrayList<>(List.of(6308317056L , 5481012733L, 1510734672L,7070992511L));
//642182669L

                String[] parts = message_text.split(" ", 2);
                if (parts.length == 2) {
                    String mensaje = parts[1];

                    for (Long chatId : groupIds) {


                        sendText(chatId, "Enviado por: " + nickname);
                        sendText(chatId, mensaje);
                    }


                } else {
                    sendText(chat_id, "Formato incorrecto. Use: /grupal + (mensaje)");
                }

            }else{
            sendText(chat_id, "BIENVENIDO A MI CHAT_BOT, A CONTINUACION TE PRESENTO LOS COMANDOS DISPONIBLES.. ");
            sendText(chat_id, "Para saber mi informacion personal poner: /info");
            sendText(chat_id, "Para saber como me parece la clase de progra es: /progra");
            sendText(chat_id, "Para hacer una conversion de Euro a quetzales poner: /cambio + (cantidad)");
            sendText(chat_id, "Si quieres un saludo especial poner: /hola");
            sendText(chat_id, "Si quieres mandar un mensaje al grupo predetermindo poner: /grupal + (Mensaje)");
        }


            System.out.println("Id: "+chat_id+ " Nombre: "+nombre+" Apellido: "+apellido);
            System.out.println("envió: "+message_text);
        }

    }

    public void sendText(Long who, String what){
        SendMessage sm = SendMessage.builder()
                .chatId(who.toString()) //Who are we sending a message to
                .text(what).build();    //Message content
        try {
            execute(sm);                        //Actually sending the message
        } catch (TelegramApiException e) {
            throw new RuntimeException(e);      //Any error will be printed here
        }
    }

}
