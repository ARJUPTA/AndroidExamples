package com.arjupta.customauth;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.arjupta.customauth.network.ApiConfig;
import com.arjupta.customauth.network.ApiManager;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.UnsupportedEncodingException;
import java.util.Calendar;
import java.util.Date;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    static final int ADD_PROFILE = 1000;
    String TAG = "LogTag";
    String hash = "";
//
//    @Bind(R.id.rv_profile)
//    RecyclerView rvProfile;
//
//    private ProfileAdapter profileAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void onClick(View view){
        EditText uid = findViewById(R.id.uid);
        EditText pass = findViewById(R.id.pass);
        hash = MD5(uid.getText()+ ApiConfig.key);
        Log.d(TAG, "onStart: "+hash);
        Call<String> addProfileCallback = ApiManager.getApiClient().postProfile(uid.getText().toString(), hash);
        addProfileCallback.enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {
                Toast.makeText(MainActivity.this, "onStart", Toast.LENGTH_SHORT).show();
                Log.d(TAG, "onResponse: "+response.body());
                if (response.isSuccessful()) {
//                    Date currentTime = Calendar.getInstance().getTime();
                    String body = response.body().toString();
//                    FirebaseAuth.getInstance().signInWithCustomToken(body)
//                            .addOnSuccessListener(new OnSuccessListener<AuthResult>() {
//                                @Override
//                                public void onSuccess(AuthResult authResult) {
//                                    Toast.makeText(MainActivity.this, authResult.toString(), Toast.LENGTH_SHORT).show();
//                                }
//                            })
//                            .addOnFailureListener(new OnFailureListener() {
//                                @Override
//                                public void onFailure(@NonNull Exception e) {
//                                    Toast.makeText(MainActivity.this, e.getMessage(), Toast.LENGTH_SHORT).show();
//                                    Log.d(TAG, "No User Found");
//                                }
//                            });
                    Toast.makeText(MainActivity.this, body, Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(MainActivity.this, "Failed to fetch", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<String> call, Throwable t) {
                Toast.makeText(MainActivity.this, "failed with " + t.getMessage(), Toast.LENGTH_SHORT).show();
                Log.d(TAG, "onFailure: " + t.getMessage());
                t.printStackTrace();
            }
        });
    }

    public String MD5(String md5) {
        try {
            java.security.MessageDigest md = java.security.MessageDigest.getInstance("MD5");
            byte[] array = md.digest(md5.getBytes("UTF-8"));
            StringBuffer sb = new StringBuffer();
            for (int i = 0; i < array.length; ++i) {
                sb.append(Integer.toHexString((array[i] & 0xFF) | 0x100).substring(1,3));
            }
            return sb.toString();
        } catch (java.security.NoSuchAlgorithmException e) {
            Log.d(TAG, "MD5: "+e.getMessage());
            Toast.makeText(MainActivity.this,"Hash "+e.getMessage(),Toast.LENGTH_LONG).show();
        } catch(UnsupportedEncodingException ex){
            Log.d(TAG, "MD5: "+ex.getMessage());
            Toast.makeText(MainActivity.this,"Hash "+ex.getMessage(),Toast.LENGTH_LONG).show();
        }
        return "pass";
    }

    public String writeJSON() {
        JSONObject object = new JSONObject();
        Date currentTime = Calendar.getInstance().getTime();
        try {
            object.put("iss", "firebase-adminsdk-poa8i@gglabs-896e5.iam.gserviceaccount.com");
            object.put("sub", "firebase-adminsdk-poa8i@gglabs-896e5.iam.gserviceaccount.com");
            object.put("aud", "https://identitytoolkit.googleapis.com/google.identity.identitytoolkit.v1.IdentityToolkit");
            object.put("iat", currentTime.getTime());
            object.put("exp",currentTime.getTime()+60*60);
            object.put("uid","uid123");
            return object.toString();
        } catch (JSONException e) {
            e.printStackTrace();
        }
        System.out.println(object);
        return null;
    }
}
    //        ButterKnife.bind(this);
//
//        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
//        setSupportActionBar(toolbar);
//
//        setProfileLayout();
//        fetchData();
//
//        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
//        fab.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Intent intent = new Intent(MainActivity.this, AddProfileActivity.class);
//                startActivityForResult(intent, ADD_PROFILE);
//            }
//        });
//    }

//    private void setProfileLayout() {
//        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
//        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
//        rvProfile.setLayoutManager(layoutManager);
//
//        profileAdapter = new ProfileAdapter();
//    }

//    private void fetchData() {
//        Call<ProfileListResponse> profileListCallback = ApiManager.getApiClient().getProfiles();
//        profileListCallback.enqueue(new Callback<ProfileListResponse>() {
//            @Override
//            public void onResponse(Call<ProfileListResponse> call, Response<ProfileListResponse> response) {
//                if (response.isSuccess()) {
//                    ProfileListResponse profileListResponse = response.body();
//                    List<Profile> profileList = profileListResponse.getProfileList();
//                    Log.d("PROFILE LIST", profileList.size() + "");
//                    if (profileList != null && profileList.size() > 0) {
//                        profileAdapter.setItems(profileList);
//                        rvProfile.setAdapter(profileAdapter);
//                    }
//
//                    Snackbar.make(rvProfile, "Successful", Snackbar.LENGTH_SHORT).show();
//                } else {
//                    Snackbar.make(rvProfile, "No profiles", Snackbar.LENGTH_SHORT).show();
//                }
//            }
//
//            @Override
//            public void onFailure(Call<ProfileListResponse> call, Throwable t) {
//                t.printStackTrace();
//            }
//        });
//    }
//
//    @Override
//    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
//        if (requestCode == ADD_PROFILE) {
//            if (resultCode == RESULT_OK) {
//                Profile newProfile = new Profile();
//                newProfile.setId(data.getLongExtra("id", -1));
//                newProfile.setName(data.getStringExtra("name"));
//                newProfile.setContactNo(data.getStringExtra("contact_no"));
//                newProfile.setEmail(data.getStringExtra("email"));
//
//                profileAdapter.addItem(newProfile);
//                profileAdapter.notifyDataSetChanged();
//            }
//        }
//    }
//}

