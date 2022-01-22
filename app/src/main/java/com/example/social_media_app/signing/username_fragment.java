package com.example.social_media_app.signing;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.social_media_app.MainActivity;
import com.example.social_media_app.R;
import com.example.social_media_app.databinding.FragmentUsernameFragmentBinding;
import com.example.social_media_app.splash_activity;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.GoogleAuthProvider;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;


public class username_fragment extends Fragment {
    FragmentUsernameFragmentBinding binding;
    private static username_fragment instance;

    public username_fragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = FragmentUsernameFragmentBinding.inflate(inflater, container, false);
        View view = binding.getRoot();
        login_fragment login_fragment = new login_fragment();
        signup_fragment signup_fragment = new signup_fragment();
        FragmentTransaction transaction = getParentFragmentManager().beginTransaction();
        binding.signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                transaction.replace(R.id.linear, signup_fragment)
                        .addToBackStack(null).
                        commit();
            }
        });

        binding.login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                transaction.replace(R.id.linear, login_fragment);
                transaction.addToBackStack(null);
                transaction.commit();
            }
        });

        GoogleSignInClient googleSignInClient = create_request();
        binding.google.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                signIn(googleSignInClient);
            }
        });

        instance = this;

        return view;
    }

    public static username_fragment getInstance() {
        return instance;
    }

    public GoogleSignInClient create_request() {
        try {
            GoogleSignInOptions gso = new GoogleSignInOptions
                    .Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                    .requestIdToken(getString(R.string.default_web_client_id))
                    .requestEmail()
                    .build();
            GoogleSignInClient googleSignInClient = GoogleSignIn.getClient(getContext(), gso);
            return googleSignInClient;

        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public void signIn(GoogleSignInClient googleSignInClient) {
        final int RC_SIGN_IN = 3;
        Intent signInIntent = googleSignInClient.getSignInIntent();
        startActivityForResult(signInIntent, RC_SIGN_IN);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        final int RC_SIGN_IN = 3;
        // Result returned from launching the Intent from GoogleSignInApi.getSignInIntent(...);
        if (requestCode == RC_SIGN_IN) {
            Task<GoogleSignInAccount> task = GoogleSignIn.getSignedInAccountFromIntent(data);
            try {
                // Google Sign In was successful, authenticate with Firebase
                GoogleSignInAccount account = task.getResult(ApiException.class);
                Log.d("onactivityres", "firebaseAuthWithGoogle:" + account.getId());
                firebaseAuthWithGoogle(account.getIdToken());
            } catch (ApiException e) {
                // Google Sign In failed, update UI appropriately
                Log.w("onactivityfailure", "Google sign in failed", e);
            }
        }
    }

    public void firebaseAuthWithGoogle(String idToken) {
        FirebaseAuth auth = FirebaseAuth.getInstance();
        AuthCredential credential = GoogleAuthProvider.getCredential(idToken, null);
        auth.signInWithCredential(credential)
                .addOnCompleteListener(getActivity(), new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            SharedPreferences sharedPreferences = getContext().getSharedPreferences("gender_file", Context.MODE_PRIVATE);
                            if (sharedPreferences.contains("signin_with_google")) {
                                Toast.makeText(getContext(), "Login success", Toast.LENGTH_SHORT).show();
                                Intent intent = new Intent(getContext(), MainActivity.class);
                                startActivity(intent);
                            } else {
                                FirebaseFirestore db = FirebaseFirestore.getInstance();
                                String userid = auth.getCurrentUser().getUid();
                                DocumentReference documentReference = db.collection("Boys").document(userid);
                                DocumentReference documentReference1 = db.collection("Girls").document(userid);
                                documentReference.get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
                                    @Override
                                    public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                                        if (task.isSuccessful()) {
                                            DocumentSnapshot document = task.getResult();
                                            if (document.exists()) {
                                                SharedPreferences.Editor editor = sharedPreferences.edit();
                                                editor.putString("gender_key", "Girls");
                                                editor.putString("user_gender_key", "Boys");
                                                editor.putBoolean("signin_with_google", true);
                                                editor.apply();
                                                Toast.makeText(getContext(), "Login success", Toast.LENGTH_SHORT).show();
                                                Intent intent = new Intent(getContext(), MainActivity.class);
                                                startActivity(intent);
                                            } else {
                                                documentReference1.get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
                                                    @Override
                                                    public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                                                        if (task.isSuccessful()) {
                                                            DocumentSnapshot document = task.getResult();
                                                            if (document.exists()) {
                                                                SharedPreferences.Editor editor = sharedPreferences.edit();
                                                                editor.putString("gender_key", "Boys");
                                                                editor.putString("user_gender_key", "Girls");
                                                                editor.putBoolean("signin_with_google", true);
                                                                editor.apply();
                                                                Toast.makeText(getContext(), "Login success", Toast.LENGTH_SHORT).show();
                                                                Intent intent = new Intent(getContext(), MainActivity.class);
                                                                startActivity(intent);
                                                            } else {
                                                                Toast.makeText(getContext(), "Complete your Profile", Toast.LENGTH_SHORT).show();
                                                                FirebaseUser user = auth.getCurrentUser();
                                                                String userid = user.getUid();
                                                                String email = "";
                                                                GoogleSignInAccount account = GoogleSignIn.getLastSignedInAccount(getContext());
                                                                if (account != null) {
                                                                    email = account.getEmail();
                                                                }
                                                                Bundle bundle = new Bundle();
                                                                bundle.putString("userid", userid);
                                                                bundle.putString("email", email);
                                                                google_sign_in_info_fragment google_sign_in_info_fragment = new google_sign_in_info_fragment();
                                                                FragmentTransaction transaction = getParentFragmentManager().beginTransaction();
                                                                google_sign_in_info_fragment.setArguments(bundle);
                                                                transaction.replace(R.id.linear, google_sign_in_info_fragment).commit();
                                                            }
                                                        }
                                                    }
                                                }).addOnFailureListener(new OnFailureListener() {
                                                    @Override
                                                    public void onFailure(@NonNull Exception e) {

                                                    }
                                                });
                                            }
                                        }
                                    }
                                }).addOnFailureListener(new OnFailureListener() {
                                    @Override
                                    public void onFailure(@NonNull Exception e) {

                                    }
                                });
                            }
                        } else {
                            // If sign in fails, display a message to the user.
                            SharedPreferences sharedPreferences = getContext().getSharedPreferences("gender_file", Context.MODE_PRIVATE);
                            if (sharedPreferences.contains("signin_with_google")) {
                                Toast.makeText(getContext(), "Login failed", Toast.LENGTH_SHORT).show();
                            } else {
                                Toast.makeText(getContext(), "signIn failed", Toast.LENGTH_SHORT).show();
                            }
                        }
                    }
                });
    }
}