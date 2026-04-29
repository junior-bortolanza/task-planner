package com.bortolanza.agendadortarefas

import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.cloud.openfeign.EnableFeignClients

@SpringBootApplication
@EnableFeignClients
class AgendadorTarefasApplication {

	static void main(String[] args) {
		SpringApplication.run(AgendadorTarefasApplication, args)
	}

}
