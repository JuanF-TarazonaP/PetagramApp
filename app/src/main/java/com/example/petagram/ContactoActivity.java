package com.example.petagram;

import android.os.Bundle;
import android.text.TextUtils;
import android.util.Patterns;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.button.MaterialButton;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

import java.util.Properties;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import javax.mail.Address;
import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

/**
 * Formulario de contacto que envía el comentario por correo usando JavaMail.
 */
public class ContactoActivity extends AppCompatActivity {

    private TextInputLayout tilNombre, tilCorreo, tilMensaje;
    private TextInputEditText etNombre, etCorreo, etMensaje;
    private MaterialButton btnEnviar;

    // El envío de correo usa la red, así que se hace fuera del hilo principal
    private final ExecutorService executor = Executors.newSingleThreadExecutor();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contacto);

        if (getSupportActionBar() != null) {
            getSupportActionBar().setTitle(R.string.titulo_contacto);
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }

        tilNombre = findViewById(R.id.tilNombre);
        tilCorreo = findViewById(R.id.tilCorreo);
        tilMensaje = findViewById(R.id.tilMensaje);
        etNombre = findViewById(R.id.etNombre);
        etCorreo = findViewById(R.id.etCorreo);
        etMensaje = findViewById(R.id.etMensaje);
        btnEnviar = findViewById(R.id.btnEnviar);

        btnEnviar.setOnClickListener(v -> {
            if (validar()) {
                enviarCorreo(texto(etNombre), texto(etCorreo), texto(etMensaje));
            }
        });
    }

    private void enviarCorreo(String nombre, String correo, String mensaje) {
        if (TextUtils.isEmpty(BuildConfig.MAIL_USUARIO) || TextUtils.isEmpty(BuildConfig.MAIL_PASSWORD)) {
            Toast.makeText(this, R.string.correo_sin_config, Toast.LENGTH_LONG).show();
            return;
        }

        btnEnviar.setEnabled(false);
        btnEnviar.setText(R.string.enviando);

        executor.execute(() -> {
            String error = null;
            try {
                Properties props = new Properties();
                props.put("mail.smtp.host", "smtp.gmail.com");
                props.put("mail.smtp.port", "587");
                props.put("mail.smtp.auth", "true");
                props.put("mail.smtp.starttls.enable", "true");

                Session session = Session.getInstance(props, new Authenticator() {
                    @Override
                    protected PasswordAuthentication getPasswordAuthentication() {
                        return new PasswordAuthentication(BuildConfig.MAIL_USUARIO, BuildConfig.MAIL_PASSWORD);
                    }
                });

                String destino = TextUtils.isEmpty(BuildConfig.MAIL_DESTINO)
                        ? BuildConfig.MAIL_USUARIO : BuildConfig.MAIL_DESTINO;

                MimeMessage msg = new MimeMessage(session);
                msg.setFrom(new InternetAddress(BuildConfig.MAIL_USUARIO, "Petagram"));
                msg.setReplyTo(new Address[]{new InternetAddress(correo)});
                msg.setRecipients(Message.RecipientType.TO, InternetAddress.parse(destino));
                msg.setSubject("Nuevo comentario de " + nombre + " - Petagram");
                msg.setText("Nombre: " + nombre
                        + "\nCorreo: " + correo
                        + "\n\nMensaje:\n" + mensaje);

                Transport.send(msg);
            } catch (Exception e) {
                error = e.getMessage();
            }

            final String errorFinal = error;
            runOnUiThread(() -> {
                btnEnviar.setEnabled(true);
                btnEnviar.setText(R.string.btn_enviar);
                if (errorFinal == null) {
                    Toast.makeText(this, R.string.correo_enviado, Toast.LENGTH_LONG).show();
                    etNombre.setText("");
                    etCorreo.setText("");
                    etMensaje.setText("");
                } else {
                    Toast.makeText(this, getString(R.string.correo_error, errorFinal),
                            Toast.LENGTH_LONG).show();
                }
            });
        });
    }

    private boolean validar() {
        boolean ok = requerido(tilNombre, etNombre);
        ok &= requerido(tilMensaje, etMensaje);

        String correo = texto(etCorreo);
        if (TextUtils.isEmpty(correo)) {
            tilCorreo.setError(getString(R.string.error_requerido));
            ok = false;
        } else if (!Patterns.EMAIL_ADDRESS.matcher(correo).matches()) {
            tilCorreo.setError(getString(R.string.error_correo));
            ok = false;
        } else {
            tilCorreo.setError(null);
        }
        return ok;
    }

    private boolean requerido(TextInputLayout til, TextInputEditText et) {
        if (TextUtils.isEmpty(texto(et))) {
            til.setError(getString(R.string.error_requerido));
            return false;
        }
        til.setError(null);
        return true;
    }

    private String texto(TextInputEditText et) {
        return et.getText() == null ? "" : et.getText().toString().trim();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        executor.shutdown();
    }
}
