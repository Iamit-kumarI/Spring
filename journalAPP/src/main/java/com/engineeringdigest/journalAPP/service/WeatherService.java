package com.engineeringdigest.journalAPP.service;

import com.engineeringdigest.journalAPP.api.response.WeatherResponse;
import com.engineeringdigest.journalAPP.cache.AppCache;
import com.engineeringdigest.journalAPP.constants.PlaceHolders;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
//@Component
public class WeatherService {
    @Value("${weather.api.key}")
    private String apiKey;
//    private static final String API="http://api.weatherstack.com/current?access_key=API_KEY&query=CITY";

    @Autowired
    private AppCache appCache;
    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private RedisService redisService;

    public WeatherResponse getWeather(String city){
        WeatherResponse weatherResponse = redisService.get("weather_of : " + city, WeatherResponse.class);
        //now check if this data is already in cache then return from there don't call api again
        if(weatherResponse!=null)return weatherResponse;
        else{
            String finalAPI=appCache.appCache.get(AppCache.keys.WEATHER_API.toString()).replace(PlaceHolders.CITY,city).replace(PlaceHolders.API_KEYS,apiKey);
            ResponseEntity<WeatherResponse> response = restTemplate.exchange(finalAPI, HttpMethod.GET, null, WeatherResponse.class);
            //since we hit the api for the first time so we need to store this data to our redis cache
            WeatherResponse body = response.getBody();
            if(body!=null)redisService.set("weather_of : "+city,body,300L);//saving  data in cache for other responses
            return body;
        }
    }
    //doing postcall suppose my api requires some daya
//    String requestBody="{\n"+
//            "\"userName\":\"vipul\",\n"+
//            "\"password\":\"admin123\",\n"+
//            "}";
//    HttpHeaders httpHeaders=new HttpHeaders();
//    httpHeaders.set("key":"value");
//    HttpEntity<String>httpEntity=new HttpEntity<>(requestBody,httpHeaders);
//    ResponseEntity<WeatherResponse> response = restTemplate.exchange(finalAPI, HttpMethod.POST, requestBody, WeatherResponse.class);
//        return response.getBody();
}
