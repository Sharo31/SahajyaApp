package com.example.vaibhav.sahajya2;

import android.app.ProgressDialog;
import android.content.Intent;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.android.gms.tasks.Continuation;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.OnPausedListener;
import com.google.firebase.storage.OnProgressListener;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class FoundPersonData extends AppCompatActivity {

    //abcdefgh
    //vaibhav seth

    private static final int CHOOSE_IMAGE = 101;
    private EditText lostname;
    private EditText lostage;
    private  EditText lostdesc;
    private ImageView uploadphoto;
    private Button submitlost;
    private String lname;
    private String lname2;
    private String lage;
    private String ldesc;
    private FirebaseFirestore mFirestore;
    private StorageReference mStorage;
    private ProgressDialog progressDialog;
    private String profileImageURL;
    private String purl;
    Uri uri;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_found_person_data);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Found Person Report");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        lostname=(EditText)findViewById(R.id.editTextname);
        lostage=(EditText)findViewById(R.id.editTextage);
        lostdesc=(EditText)findViewById(R.id.editTextdesc);
        uploadphoto=(ImageView)findViewById(R.id.imageViewupload);
        submitlost=(Button)findViewById(R.id.buttonsubmitlost);
        progressDialog=new ProgressDialog(this);
        mFirestore=FirebaseFirestore.getInstance();
        mStorage=FirebaseStorage.getInstance().getReference();
        uploadphoto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(Intent.ACTION_PICK);
                intent.setType("image/*");
                startActivityForResult(intent,CHOOSE_IMAGE);
            }
        });
        submitlost.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(lostname.getText().toString().isEmpty()&&lostage.getText().toString().isEmpty()&&lostdesc.getText().toString().isEmpty()){
                    Toast.makeText(getApplicationContext(),"Fill in all the details",Toast.LENGTH_SHORT).show();

                }
                else{

                    lname=lostname.getText().toString();
                    lname2=lname;
                    lage=lostage.getText().toString();
                    ldesc=lostdesc.getText().toString();
               /*Bundle bundle = new Bundle();
                bundle.putString("name",lname);
                bundle.putString("age",lage);
                bundle.putString("desc",ldesc);
                LostTab frag = new LostTab();
                frag.setArguments(bundle);*/
                    Map<String,String> userMap=new HashMap<>();
                    userMap.put("name",lname);
                    userMap.put("desc",ldesc);
                    userMap.put("age",lage);
                    String timeStamp = new SimpleDateFormat("dd/MM/yyyy hh:mm:ss").format(new Date());
                    userMap.put("time",timeStamp);
                    userMap.put("purl",purl);
                    mFirestore.collection("FoundPersonData").add(userMap).addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                        @Override
                        public void onSuccess(DocumentReference documentReference) {
                            Toast.makeText(getApplicationContext(),"Details uploaded succesfully",Toast.LENGTH_SHORT).show();
                        }
                    }).addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            String error=e.getMessage();
                            Toast.makeText(getApplicationContext(),"Error: "+error,Toast.LENGTH_SHORT).show();
                        }
                    });

                    Intent intent=new Intent(getApplicationContext(),Missing_people.class);
                    finish();
                    startActivity(intent);

                }


            }
        });

    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode==CHOOSE_IMAGE&&resultCode==RESULT_OK && data!=null && data.getData()!=null){

            uri=data.getData();
            lname2=lostname.getText().toString();
            uploadImagetoFirebaseStorage(lname2);


        }
    }
    private void setpURL(String a){
        purl=a;
    }
    private void uploadImagetoFirebaseStorage(String p) {
        final StorageReference filepaath=mStorage.child("LPhotos").child(p);
        UploadTask uploadTask=  filepaath.putFile(uri);
        uploadTask.addOnProgressListener(new OnProgressListener<UploadTask.TaskSnapshot>() {
            @Override
            public void onProgress(UploadTask.TaskSnapshot taskSnapshot) {
                double progress = (100.0 * taskSnapshot.getBytesTransferred()) / taskSnapshot.getTotalByteCount();
                System.out.println("Upload is " + progress + "% done");
                int currentprogress = (int) progress;
                progressDialog.setProgress(currentprogress);
                progressDialog.setMessage("Uploading  " + currentprogress + "%" );
                progressDialog.show();
            }
        }).addOnPausedListener(new OnPausedListener<UploadTask.TaskSnapshot>() {
            @Override
            public void onPaused(UploadTask.TaskSnapshot taskSnapshot) {
                System.out.println("Upload is paused");
            }
        });
        uploadTask.addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
            @Override
            public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {

                Toast.makeText(getApplicationContext(),"Done",Toast.LENGTH_SHORT).show();
                progressDialog.dismiss();
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(getApplicationContext(),"Not Done"+e.getMessage(),Toast.LENGTH_SHORT).show();
            }
        });
        Task<Uri> urlTask = uploadTask.continueWithTask(new Continuation<UploadTask.TaskSnapshot, Task<Uri>>() {
            @Override
            public Task<Uri> then(@NonNull Task<UploadTask.TaskSnapshot> task) throws Exception {
                if (!task.isSuccessful()) {
                    throw task.getException();
                }

                // Continue with the task to get the download URL
                return filepaath.getDownloadUrl();
            }
        }).addOnCompleteListener(new OnCompleteListener<Uri>() {
            @Override
            public void onComplete(@NonNull Task<Uri> task) {
                if (task.isSuccessful()) {
                    Uri downloadUri = task.getResult();
                    profileImageURL=downloadUri.toString();
                    setpURL(profileImageURL);
                } else {
                    // Handle failures
                    // ...
                }
            }
        });


    }
}

