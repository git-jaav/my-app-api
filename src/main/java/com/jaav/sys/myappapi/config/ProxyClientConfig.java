package com.jaav.sys.myappapi.config;



import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.jaav.sys.myappapi.client.AnotherServiceClient;
import okhttp3.OkHttpClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

@Configuration
//@ConditionalOnProperty(value = "${application.process.stack-another-service}")
public class ProxyClientConfig {


    @Autowired
    ApplicationProperties applicationProperties;

    @Value("${application.http-client.another-service.url}")
    private String baseUrlAnotherService;

    @Bean
    @Autowired
    public Retrofit retrofit(OkHttpClient client, Gson gson) {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(baseUrlAnotherService)
                .client(client)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .addConverterFactory(GsonConverterFactory.create())
                //.addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .build();
        return retrofit;
    }

    @Bean
    public OkHttpClient client() {
        final OkHttpClient.Builder okHttpClientBuilder = new OkHttpClient.Builder();

        final OkHttpClient builtClient = okHttpClientBuilder.build();

        return builtClient;
    }

    @Bean
    public Gson gson() {
        return new GsonBuilder().setLenient().create();
    }


    // Create a Bean for svcInvestmentClient and add it to SpringContext.
    @Bean
    public AnotherServiceClient anotherServiceClient() {
        return retrofit(client(),gson())
                .create(AnotherServiceClient.class);
    }

    /*
    // Create Retrofit instance of svcInvestment
    private Retrofit createHttpClient(String baseUrl,String connectionTimeout,String readTimeout,
                                      ObjectMapper objectMapper) {
        OkHttpClient.Builder httpClient = new OkHttpClient.Builder()
                .connectTimeout(Integer.parseInt(connectionTimeout), TimeUnit.MILLISECONDS)
                .readTimeout(Integer.parseInt(readTimeout),TimeUnit.MILLISECONDS);
        return new Retrofit.Builder()
                .baseUrl("http://localhost/api/business/miniencuesta/v1/")
                .addConverterFactory(GsonConverterFactory.create())
                .client(httpClient.build())
                .build();

    }*/

}
