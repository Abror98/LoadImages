package com.example.loadimages;

        import java.util.List;

        import retrofit2.Call;
        import retrofit2.http.GET;

public interface RecInterface {

    String JSONURL = "https://jsonplaceholder.typicode.com/";

    @GET("photos")
    Call<List<ModelRec>> getString();
}
