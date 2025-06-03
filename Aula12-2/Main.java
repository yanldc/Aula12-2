public class Main {
    public static void main(String[] args) {
        configurarNotificacao("urgente", "Servidor fora do ar");
        configurarNotificacao("normal", "Atualização completa");
    }

    public static void configurarNotificacao(String prioridade, String mensagem) {
        Notificacao notificacao = new NotificacaoSimples();

        if (prioridade.equals("urgente")) {
            notificacao = new SMSDecorator(notificacao);
            notificacao = new EmailDecorator(notificacao);
            notificacao = new SlackDecorator(notificacao);
            notificacao = new WhatsAppDecorator(notificacao); // WhatsApp incluído
        } else {
            notificacao = new EmailDecorator(notificacao);
            notificacao = new WhatsAppDecorator(notificacao); // WhatsApp incluído também
        }

        System.out.println("Prioridade " + prioridade + ": " + notificacao.enviar(mensagem));
    }
}
