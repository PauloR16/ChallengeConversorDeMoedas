package api;
import com.google.gson.annotations.SerializedName;
import java.util.Map;

public class ExchangeResponse {
    @SerializedName("conversion_rates")
    public Map<String, Double> conversionRates;

    @SerializedName("time_next_update_utc")
    public String timeNextUpdateUtc;

    @SerializedName("time_last_update_utc")
    public String timeLastUpdateUtc;
}