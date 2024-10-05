package ProjetoPI.ProjetoDoadores.controller

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.web.servlet.config.annotation.CorsRegistry
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer

@Configuration
class WebConfig : WebMvcConfigurer {

    @Bean
    fun corsConfigurer(): WebMvcConfigurer {
        return object : WebMvcConfigurer {
            override fun addCorsMappings(registry: CorsRegistry) {
                // Permite requisições CORS do seu frontend rodando nas URLs especificadas
                registry.addMapping("/**").allowedOrigins("http://127.0.0.1:5500", "http://localhost:5500")
            }
        }
    }
}
