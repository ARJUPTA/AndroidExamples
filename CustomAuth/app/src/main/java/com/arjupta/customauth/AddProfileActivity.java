//package com.arjupta.customauth;
//
//import android.content.Intent;
//import android.os.Bundle;
//import android.support.design.widget.Snackbar;
//import android.support.v7.app.AppCompatActivity;
//import android.text.TextUtils;
//import android.widget.EditText;
//import android.widget.Toast;
//
//import butterknife.Bind;
//import butterknife.ButterKnife;
//import butterknife.OnClick;
//import example.com.retrofit.model.Profile;
//import example.com.retrofit.network.ApiManager;
//import retrofit2.Call;
//import retrofit2.Callback;
//import retrofit2.Response;
//
///**
// * Created by michaellimantara on 20/3/16.
// */
//public class AddProfileActivity extends AppCompatActivity {
//
//    @Bind(R.id.et_name)
//    EditText etName;
//
//    @Bind(R.id.et_contact_no)
//    EditText etContactNo;
//
//    @Bind(R.id.et_email)
//    EditText etEmail;
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_add_profile);
//        ButterKnife.bind(this);
//    }
//
//    @OnClick(R.id.btn_add_profile)
//    public void handleClick() {
//        final String name = etName.getText().toString();
//        final String contactNo = etContactNo.getText().toString();
//        final String email = etEmail.getText().toString();
//
//        if (TextUtils.isEmpty(name) || TextUtils.isEmpty(contactNo) || TextUtils.isEmpty(email)) {
//            Toast.makeText(AddProfileActivity.this,
//                    "Name, Contact No. and Email are required fields",
//                    Toast.LENGTH_SHORT).show();
//            return;
//        }
//
//        Call<Profile> addProfileCallback = ApiManager.getApiClient()
//                .postProfile(name, contactNo, email);
//
//        addProfileCallback.enqueue(new Callback<Profile>() {
//            @Override
//            public void onResponse(Call<Profile> call, Response<Profile> response) {
//                if (response.isSuccess()) {
//                    Profile profile = response.body();
//
//                    Intent returnIntent = new Intent();
//                    returnIntent.putExtra("id", profile.getId());
//                    returnIntent.putExtra("name", name);
//                    returnIntent.putExtra("contact_no", contactNo);
//                    returnIntent.putExtra("email", email);
//                    setResult(RESULT_OK, returnIntent);
//                    finish();
//                } else {
//                    Toast.makeText(AddProfileActivity.this,
//                            "Failed to add new Profile", Toast.LENGTH_SHORT).show();
//                }
//            }
//
//            @Override
//            public void onFailure(Call<Profile> call, Throwable t) {
//                t.printStackTrace();
//            }
//        });
//    }
//}
