// create an instance of firebase auth
/*private FirebaseAuth mAuth;
private void sendVerificationEmail()
        {
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();

        user.sendEmailVerification()
        .addOnCompleteListener(new OnCompleteListener<Void>() {
@Override
public void onComplete(@NonNull Task<Void> task) {
        if (task.isSuccessful()) {
        // email sent


        // after email is sent just logout the user and finish this activity
        FirebaseAuth.getInstance().signOut();
        startActivity(new Intent(SignupActivity.this, LoginActivity.class));
        finish();
        }
        else
        {
        // email not sent, so display message and restart the activity or do whatever you wish to do

        //restart this activity
        overridePendingTransition(0, 0);
        finish();
        overridePendingTransition(0, 0);
        startActivity(getIntent());

        }
        }
        });
        }




        mAuth = FirebaseAuth.getInstance();
        mAuth.createUserWithEmailAndPassword(email, password)
        .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
@Override
public void onComplete(@NonNull Task<AuthResult> task) {
        Log.d("TAG", "createUserWithEmail:onComplete:" + task.isSuccessful());

        // If sign in fails, display a message to the user. If sign in succeeds
        // the auth state listener will be notified and logic to handle the
        // signed in user can be handled in the listener.
        if (!task.isSuccessful()) {
        // Show the message task.getException()
        }
        else
        {
        // successfully account created
        // now the AuthStateListener runs the onAuthStateChanged callback
        mAuthListener = new FirebaseAuth.AuthStateListener() {
@Override
public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
        FirebaseUser user = firebaseAuth.getCurrentUser();
        if (user != null) {
        // User is signed in
        // NOTE: this Activity should get onpen only when the user is not signed in, otherwise
        // the user will receive another verification email.
        sendVerificationEmail();
        } else {
        // User is signed out

        }
        }
        };*/