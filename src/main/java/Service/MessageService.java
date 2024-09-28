package Service;

import DAO.MessageDAO;
import DAO.MessageDAOImpl;
import Model.Message;
import java.util.List;

public class MessageService {
    private MessageDAO messageDAO;
    private AccountService accountService;

    public MessageService() {
        this.messageDAO = new MessageDAOImpl();
        this.accountService = new AccountService();
    }

    public Message createMessage(Message message) {
        // Validate message_text not blank and less than 255 characters
        if(message.getMessage_text() == null || message.getMessage_text().isEmpty()) {
            return null;
        }
        if(message.getMessage_text().length() >= 255) {
            return null;
        }
        // Validate posted_by exists
        if(accountService.getAccountById(message.getPosted_by()) == null) {
            return null;
        }
        return messageDAO.createMessage(message);
    }

    public Message getMessageById(int id) {
        return messageDAO.getMessageById(id);
    }

    public List<Message> getAllMessages() {
        return messageDAO.getAllMessages();
    }

    public List<Message> getMessagesByAccountId(int accountId) {
        return messageDAO.getMessagesByAccountId(accountId);
    }

    public Message updateMessage(int id, String message_text) {
        // Validate message_text
        if(message_text == null || message_text.isEmpty()) {
            return null;
        }
        if(message_text.length() >= 255) {
            return null;
        }
        Message existingMessage = messageDAO.getMessageById(id);
        if(existingMessage != null) {
            existingMessage.setMessage_text(message_text);
            return messageDAO.updateMessage(existingMessage);
        }
        return null;
    }

    public Message deleteMessage(int id) {
        return messageDAO.deleteMessage(id);
    }
}
