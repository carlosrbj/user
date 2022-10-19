package com.hsob.user;

import com.hsob.user.model.HERANCA_POLIM.conta.ContaCorrente;
import com.hsob.user.model.HERANCA_POLIM.conta.ContaPoupanca;
import com.hsob.user.model.HERANCA_POLIM.conta.SeguroDeVida;
import com.hsob.user.model.HERANCA_POLIM.conta.TributacaoService;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
//import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.boot.autoconfigure.web.servlet.error.ErrorMvcAutoConfiguration;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@SpringBootApplication
@EnableCaching
//@EnableEurekaClient
//@ComponentScan(basePackages = {"com.hsob.documentdb", "com.hsob.user"})
@EnableAutoConfiguration(exclude = {ErrorMvcAutoConfiguration.class})
public class UserServiceApplication {

//	public static void main(String[] args) {
//		SpringApplication.run(UserServiceApplication.class, args);
//	}
//	public static void main(String[] args) {
//		Gerente gerente = new Gerente();
//		gerente.setNome("Gerente");
//		gerente.setSalario(1500.0);
//		gerente.setSenha("123");
//
//		System.out.println("Tipo: " + gerente.getTipo());
//		System.out.println("Salário: " + gerente.getSalario());
//		System.out.println("Autenticado: " + gerente.autentica("1234"));
//		System.out.println("Bonificação: " + gerente.getBonificacao());
//		System.out.println("\n=====================================\n");
//
//		ControleBonificacao controleBonificacao = new ControleBonificacao();
//		controleBonificacao.registraBonificacao(gerente);
//		System.out.println("============     SOMA     ===========\n");
//		System.out.println("Soma: " + controleBonificacao.getSoma());
//
//	}

//	public static void main(String[] args) {
//		Autenticavel autenticavel = new Gerente();
//		Gerente gerente = new Gerente();
//		gerente.setNome("Gerente");
//		gerente.setSalario(1500.0);
//		gerente.setSenha("123");
//
//		Diretor diretor = new Diretor();
//		diretor.setNome("Diretor");
//		diretor.setSalario(2000.0);
//
//		Design design = new Design();
//		design.setNome("Designer");
//		design.setSalario(1000.0);
//
//		ControleBonificacao controleBonificacao = new ControleBonificacao();
//		controleBonificacao.registraBonificacao(gerente);
//		controleBonificacao.registraBonificacao(diretor);
//		controleBonificacao.registraBonificacao(design);
//
//		System.out.println("Tipo: " + gerente.getTipo());
//		System.out.println("Salário: " + gerente.getSalario());
//		System.out.println("Autenticado: " + gerente.autentica("1234"));
//		System.out.println("Bonificação: " + gerente.getBonificacao());
//		System.out.println("\n=====================================\n");
//		System.out.println("Tipo: " + diretor.getTipo());
//		System.out.println("Salário: " + diretor.getSalario());
//		System.out.println("Bonificação: " + diretor.getBonificacao());
//		System.out.println("\n=====================================\n");
//		System.out.println("Tipo: " + design.getTipo());
//		System.out.println("Salário: " + design.getSalario());
//		System.out.println("Bonificação: " + design.getBonificacao());
//		System.out.println("\n=====================================\n");
//
//		System.out.println("============     SOMA     ===========\n");
//		System.out.println("Soma: " + controleBonificacao.getSoma());
//	}

	public static void main(String[] args) {
		ContaCorrente cC = new ContaCorrente(111, 111);
		System.out.println("Saldo antes de depositar: " + cC.getSaldo());
		cC.deposita(200);
		System.out.println("Saldo depois de depositar: " + cC.getSaldo()+"\n");
		System.out.println("Valor Imposto Cc: " + cC.getValorImposto()+"\n");

		ContaPoupanca cP = new ContaPoupanca(222, 222);
		System.out.println("Saldo antes de depositar: " + cP.getSaldo());
		cP.deposita(100);
		System.out.println("Saldo depois de depositar: " + cP.getSaldo()+"\n");

		SeguroDeVida sV = new SeguroDeVida();
		System.out.println("Valor Imposto sV: " + sV.getValorImposto()+"\n");

		TributacaoService tributacaoService = new TributacaoService();
		tributacaoService.registraTributacao(cC);
		tributacaoService.registraTributacao(sV);
		System.out.println("Total de Imposto: " + tributacaoService.getTotalImposto()+"\n");


		cC.transfere(50, cP);
		System.out.println("Saldo CP depois de transferir: " + cP.getSaldo()+"\n");
		System.out.println("Saldo CC depois de transferir: " + cC.getSaldo()+"\n");
	}
	@Bean
	public PasswordEncoder getPasswordEncoder() {
		return new BCryptPasswordEncoder();
	}

}
