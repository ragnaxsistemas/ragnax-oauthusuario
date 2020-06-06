package com.ragnax.ragnaxoauthusuario.servicio.cliente;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;

import com.ragnax.domain.zapala.modelo.Zapala;
import com.ragnax.domain.zapala.modelo.ZapalaRequest;

//@FeignClient(name = "ragnax-zapala" , url = "${server.port.zapala}")
@FeignClient(name = "ragnax-zapala")
public interface ZapalaCliente {
	
	@PostMapping("/generar-tiempo-duracion")
	public Zapala generarTiempoDuracion(ZapalaRequest zapalaRequest);
	
	@PostMapping("/convertir-strfechaconformat-to-timestamp")
	public Zapala convertirStrFechaConFormatToTimestamp(ZapalaRequest zapalaRequest);
	
	@PostMapping("/generar-codigo-numero")
	public Zapala generarCodigoByNumero(ZapalaRequest zapalaRequest);

	@PostMapping("/generar-codigo-numero-encodear")
	public Zapala generarCodigoByNumeroByEncodear(ZapalaRequest zapalaRequest);
	
	@PostMapping("/generar-patron-rut")
	public Zapala generarPatronRUT(ZapalaRequest zapalaRequest);
	
	@PostMapping("/generar-md5")
	public Zapala generarMD5(ZapalaRequest zapalaRequest);
	
	@PostMapping("/generar-sha")
	public Zapala generarSHA(ZapalaRequest zapalaRequest);
	
	@PostMapping("/timestamp-todateutil")
	public Zapala timestampToDateUtil(ZapalaRequest zapalaRequest);
	
	@PostMapping("/dateutil-totimestamp")
	public Zapala dateUtilToTimestamp(ZapalaRequest zapalaRequest);
	
	@PostMapping("/agregar-minutos")
	public Zapala agregarMinutos(ZapalaRequest zapalaRequest);
	
}
