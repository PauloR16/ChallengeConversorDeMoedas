package calculadora;

import api.ExchangeResponse;
import com.google.gson.Gson;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.TimeZone;

public class CalculadoraConversao {
    private static final String API_KEY = "8beb5dc7e09376a0521b6864";
    private static final String BASE_URL = "https://v6.exchangerate-api.com/v6/" + API_KEY + "/latest/";

    public static void converterMoeda(String fromCurrency, String toCurrency, double valor) {
        try {
            String url = BASE_URL + fromCurrency;
            HttpClient client = HttpClient.newHttpClient();
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create(url))
                    .build();

            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

            Gson gson = new Gson();
            ExchangeResponse exchange = gson.fromJson(response.body(), ExchangeResponse.class);

            if (exchange.conversionRates.containsKey(toCurrency)) {
                double taxa = exchange.conversionRates.get(toCurrency);
                double convertido = valor * taxa;

                System.out.printf("Valor da taxa de câmbio: 1 %s = %.4f %s%n", fromCurrency, taxa, toCurrency);
                System.out.printf("Valor convertido: %.2f %s%n", convertido, toCurrency);
                System.out.println("Última atualização das taxas: " + formatarDataParaBR(exchange.timeLastUpdateUtc));
                System.out.println("Próxima atualização das taxas: " + formatarDataParaBR(exchange.timeNextUpdateUtc));
            } else {
                System.out.println("Erro: Moeda de destino não encontrada na resposta da API.");
            }

        } catch (Exception e) {
            System.out.println("Erro ao fazer a conversão: " + e.getMessage());
        }
    }

    private static String formatarDataParaBR(String dataUtc) {
        try {

            DateFormat formatoOriginal = new SimpleDateFormat("EEE, dd MMM yyyy HH:mm:ss Z", Locale.US);
            formatoOriginal.setTimeZone(TimeZone.getTimeZone("UTC"));
            Date data = formatoOriginal.parse(dataUtc);

            DateFormat formatoBR = new SimpleDateFormat("dd 'de' MMMM 'de' yyyy 'às' HH:mm", new Locale("pt", "BR"));
            formatoBR.setTimeZone(TimeZone.getTimeZone("America/Sao_Paulo"));

            return formatoBR.format(data);
        } catch (ParseException e) {
            return dataUtc;
        }
    }
}