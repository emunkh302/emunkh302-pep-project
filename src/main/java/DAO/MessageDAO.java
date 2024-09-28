package DAO;

import Model.Message;
import java.util.List;

public interface MessageDAO {
    Message createMessage(Message message);
    Message getMessageById(int id);
    List<Message> getAllMessages();
    List<Message> getMessagesByAccountId(int accountId);
    Message updateMessage(Message message);
    Message deleteMessage(int id);
}
