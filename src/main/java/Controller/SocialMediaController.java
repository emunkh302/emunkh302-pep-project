package Controller;

import Service.AccountService;
import Service.MessageService;
import Model.Account;
import Model.Message;
import io.javalin.Javalin;
import io.javalin.http.Context;

import java.util.List;

/**
 * TODO: You will need to write your own endpoints and handlers for your controller. The endpoints you will need can be
 * found in readme.md as well as the test cases. You should
 * refer to prior mini-project labs and lecture materials for guidance on how a controller may be built.
 */
public class SocialMediaController {

    private AccountService accountService;
    private MessageService messageService;

    public SocialMediaController() {
        accountService = new AccountService();
        messageService = new MessageService();
    }
    
    /**
     * In order for the test cases to work, you will need to write the endpoints in the startAPI() method, as the test
     * suite must receive a Javalin object from this method.
     * @return a Javalin app object which defines the behavior of the Javalin controller.
     */
    public Javalin startAPI() {
        Javalin app = Javalin.create();

        // Register endpoints
        app.post("/register", this::registerUser);
        app.post("/login", this::loginUser);

        // Message endpoints
        app.post("/messages", this::createMessage);
        app.get("/messages", this::getAllMessages);
        app.get("/messages/{id}", this::getMessageById);
        app.delete("/messages/{id}", this::deleteMessage);
        app.patch("/messages/{id}", this::updateMessage);
        app.get("/accounts/{id}/messages", this::getMessagesByAccountId);

        return app;
    }

    // Handlers

    private void registerUser(Context ctx) {
        Account account = ctx.bodyAsClass(Account.class);
        Account createdAccount = accountService.createAccount(account);
        if(createdAccount != null) {
            ctx.json(createdAccount);
        } else {
            ctx.status(400);
            ctx.result("");
        }
    }

    private void loginUser(Context ctx) {
        Account credentials = ctx.bodyAsClass(Account.class);
        Account account = accountService.login(credentials.getUsername(), credentials.getPassword());
        if(account != null) {
            ctx.json(account);
        } else {
            ctx.status(401);
            ctx.result("");
        }
    }

    private void createMessage(Context ctx) {
        Message message = ctx.bodyAsClass(Message.class);
        Message createdMessage = messageService.createMessage(message);
        if(createdMessage != null) {
            ctx.json(createdMessage);
        } else {
            ctx.status(400);
            ctx.result("");
        }
    }

    private void getAllMessages(Context ctx) {
        List<Message> messages = messageService.getAllMessages();
        ctx.json(messages);
    }

    private void getMessageById(Context ctx) {
        int id = Integer.parseInt(ctx.pathParam("id"));
        Message message = messageService.getMessageById(id);
        if(message != null) {
            ctx.json(message);
        } else {
            ctx.status(200);
            ctx.result("");
        }
    }

    private void deleteMessage(Context ctx) {
        int id = Integer.parseInt(ctx.pathParam("id"));
        Message deletedMessage = messageService.deleteMessage(id);
        if(deletedMessage != null) {
            ctx.json(deletedMessage);
        } else {
            ctx.status(200);
            ctx.result("");
        }
    }

    private void updateMessage(Context ctx) {
        int id = Integer.parseInt(ctx.pathParam("id"));
        Message messageUpdates = ctx.bodyAsClass(Message.class);
        Message updatedMessage = messageService.updateMessage(id, messageUpdates.getMessage_text());
        if(updatedMessage != null) {
            ctx.json(updatedMessage);
        } else {
            ctx.status(400);
            ctx.result("");
        }
    }

    private void getMessagesByAccountId(Context ctx) {
        int accountId = Integer.parseInt(ctx.pathParam("id"));
        List<Message> messages = messageService.getMessagesByAccountId(accountId);
        ctx.json(messages);
    }
}