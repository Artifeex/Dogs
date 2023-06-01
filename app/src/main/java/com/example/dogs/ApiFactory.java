package com.example.dogs;

import retrofit2.Retrofit;
import retrofit2.adapter.rxjava3.RxJava3CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class ApiFactory {

    private static ApiService apiService;
    //BASE_URL это базовая часть всех запросов. То есть, запросов может быть множество, но у них будет какая-то начальная
    //общая часть. Вот эта часть и есть BASE_URL. А End point - указывается уже в ApiSerive в
    //анотации к запросу. Например, @GET("random"). Таким образом у нас суммарно получится:
    //BASE_URLrandom или же: https://dog.ceo/api/breeds/image/random при вызове @GET("random")
    //                                                                          Single<DogImage> loadDogImage();
    private static final String BASE_URL = "https://dog.ceo/api/breeds/image/"; //всегда должен заканчиваться косой чертой

    public static ApiService getApiService() {
        if(apiService == null) {
            Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create()) //указываем какой конвертер JSON будет использовать
                    .addCallAdapterFactory(RxJava3CallAdapterFactory.create()) //добавляе поддержку rxjava
                    .build();
            apiService = retrofit.create(ApiService.class); //ретрофит для нас создает реализацию интерфейса
        }
        return apiService;
    }

}
