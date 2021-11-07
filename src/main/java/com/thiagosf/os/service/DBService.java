package com.thiagosf.os.service;

import java.util.Arrays;

import com.thiagosf.os.domain.Cliente;
import com.thiagosf.os.domain.Os;
import com.thiagosf.os.domain.Tecnico;
import com.thiagosf.os.domain.enums.Prioridade;
import com.thiagosf.os.domain.enums.Status;
import com.thiagosf.os.repositories.ClienteRepository;
import com.thiagosf.os.repositories.OsRepository;
import com.thiagosf.os.repositories.TecnicoRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DBService {

	@Autowired
	ClienteRepository clienteRepository;

	@Autowired
	OsRepository osRepository;

	@Autowired
	TecnicoRepository tecnicoRepository;

	public void instanciaDB() {
		System.out.println("Service instanciaDB() invocado.....");
		Cliente cliente1 = new Cliente(null, "Ana", "608.603.560-19", "(22)92222-2222");
		Tecnico tecnico1 = new Tecnico(null, "Thiago", "995.884.930-56", "(11)91111-1111");
		Os os1 = new Os(null, Prioridade.ALTA, "Teste OS", Status.ANDAMENTO, tecnico1, cliente1);

		cliente1.getList().add(os1);
		tecnico1.getList().add(os1);

		clienteRepository.saveAll(Arrays.asList(cliente1));
		tecnicoRepository.saveAll(Arrays.asList(tecnico1));
		osRepository.saveAll(Arrays.asList(os1));
	}

}
