package ru.solonchev.backend.service;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.mail.javamail.MimeMessagePreparator;
import org.springframework.stereotype.Service;
import ru.solonchev.backend.dto.order.FullOrder;
import ru.solonchev.backend.dto.order.OrderItem;

@Service
@RequiredArgsConstructor
public class EmailService {
    private final JavaMailSender javaMailSender;
    private final PizzaService pizzaService;

    @Value("${spring.mail.sender.email}")
    private String senderEmail;
    @Value("${spring.mail.sender.text}")
    private String senderText;

    public void sendEmail(Long userId, String receiver) {
        MimeMessagePreparator preparation = mimeMessage -> {
            MimeMessageHelper helper = new MimeMessageHelper(mimeMessage);
            helper.setTo(receiver);
            helper.setSubject("Чек Заказа Пиццы");
            helper.setText(generateReceiptHtml(userId), true);
        };
        javaMailSender.send(preparation);
    }

    private String generateReceiptHtml(Long userId) {
        StringBuilder builder = new StringBuilder();
        builder.append("<p>Благодарим Вас за выбор нашей пиццерии!</p>");
        builder.append("<p>Вот ваш электронный чек.</p>");
        FullOrder fullOrder = FullOrder.createFullOrder(pizzaService.getPizzasOfUser(userId));
        for (OrderItem item : fullOrder.getPizzas()) {
            builder.append("<p><b>").append(item.getName()).append("</b>").append(" : ").append(item.getPrice()).append("</p></br>");
        }
        builder.append("--------------------------------------------------------");
        builder.append("<h2>").append("Итого: ").append(fullOrder.getResultPrice()).append("</h2>");
        return builder.toString();
    }
}
