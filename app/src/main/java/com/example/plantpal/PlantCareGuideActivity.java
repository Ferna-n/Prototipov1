package com.example.plantpal;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class PlantCareGuideActivity extends AppCompatActivity {

    private WebView webViewCareGuide;
    private Button btnBack;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_plant_care_guide);

        webViewCareGuide = findViewById(R.id.webview_care_guide);
        btnBack = findViewById(R.id.btn_back);

        // Configurar WebView para cargar contenido HTML
        WebSettings webSettings = webViewCareGuide.getSettings();
        webSettings.setJavaScriptEnabled(true); // Habilitar JavaScript si es necesario
        webSettings.setAllowFileAccess(true); // Permitir acceso a archivos locales si es necesario
        webSettings.setDomStorageEnabled(true); // Habilitar almacenamiento DOM
        webSettings.setUseWideViewPort(true); // Asegura que la visualización de imágenes sea adaptativa
        webSettings.setLoadWithOverviewMode(true); // Escala correctamente el contenido
        webSettings.setSupportZoom(true); // Permitir hacer zoom

        // Aquí va el HTML con imágenes cargadas desde URLs, centradas
        String careGuideText = "<html><head><style>" +
                "body { font-size: 52px; text-align: center; }" + // Establecer tamaño de fuente más grande y centrar texto
                "img { display: block; margin: 0 auto; width: 80%; height: auto; }" + // Centrar imágenes
                "</style></head><body>" +
                "<h2>Guía de Cuidado de Plantas</h2>" +
                "<h3>1. Riego:</h3>" +
                "<img src=\"https://www.espectacular.com.ar/u/fotografias/m/2022/11/28/f1280x720-21097_152772_4631.png\"/>" +
                "<p>Asegúrate de regar tus plantas regularmente. La frecuencia depende del tipo de planta y del clima. " +
                "Generalmente, se recomienda regar una vez a la semana.</p>" +
                "<h3>2. Luz:</h3>" +
                "<img src=\"https://th.bing.com/th/id/OIP.YtohJhl2QmOq0TxgbxIwEAAAAA?w=460&h=345&rs=1&pid=ImgDetMain\"/>" +
                "<p>La mayoría de las plantas requieren luz natural. Colócalas en un lugar donde reciban luz indirecta, " +
                "pero evita la luz solar directa, que puede quemar las hojas.</p>" +
                "<h3>3. Suelo:</h3>" +
                "<img src=\"https://th.bing.com/th/id/OIP.OvIG2-VhkMhwYfT7KiU7hgHaEK?rs=1&pid=ImgDetMain\"/>" +
                "<p>Utiliza un sustrato adecuado según el tipo de planta. Las plantas suculentas, por ejemplo, necesitan " +
                "un suelo bien drenado.</p>" +
                "<h3>4. Fertilización:</h3>" +
                "<img src=\"https://i.pinimg.com/originals/fc/16/8b/fc168b370603340350d22a9114b0929c.jpg\"/>" +
                "<p>Aplica fertilizante cada cierto tiempo, especialmente durante la temporada de crecimiento. Sigue las " +
                "instrucciones del producto para no sobrefertilizar.</p>" +
                "<h3>5. Poda:</h3>" +
                "<img src=\"https://www.dicasonline.com/wp-content/uploads/Como-fazer-poda-nas-plantas.jpg\"/>" +
                "<p>Recorta las hojas muertas y las ramas dañadas para fomentar un crecimiento saludable.</p>" +
                "<h3>6. Plagas:</h3>" +
                "<img src=\"https://th.bing.com/th/id/OIP.BBdZRxv4Opf4wi73INDpwAHaE8?rs=1&pid=ImgDetMain\"/>" +
                "<p>Revisa regularmente tus plantas en busca de plagas y enfermedades. Si encuentras alguna, actúa " +
                "rápidamente con un tratamiento adecuado.</p>" +
                "<p>Recuerda que cada planta tiene sus necesidades específicas. ¡Investiga y cuida de tus plantas con amor!</p>" +
                "</body></html>";

        // Cargar el HTML en el WebView
        webViewCareGuide.loadDataWithBaseURL(null, careGuideText, "text/html", "UTF-8", null);

        // Configurar el botón de volver
        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(PlantCareGuideActivity.this, WelcomeActivity.class);
                startActivity(intent);
                finish(); // Opcional, para cerrar esta actividad
            }
        });
    }

    // Método para abrir el video de YouTube
    public void openVideoLink(View view) {
        String videoUrl = "https://www.youtube.com/watch?v=Y1jTRsBRh3g"; // Cambia esto por la URL del video
        Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(videoUrl));
        startActivity(intent);
    }
}
