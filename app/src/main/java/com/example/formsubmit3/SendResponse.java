package com.example.formsubmit3;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public interface SendResponse {

    @POST("https://docs.google.com/forms/d/e/1FAIpQLScDDJGbyI1I5Ylgwi0cWQ1AYqytB9WZ7gwGhzcxfuf4yEg32Q/formresponse")
    @FormUrlEncoded
    Call<Void> sendResponse(
            @Field("entry.1939394967") String name,
            @Field("entry.811902326") String email,
            @Field("entry.1263315349") String mobile,
            @Field("entry.1572343332") String age
    );

}