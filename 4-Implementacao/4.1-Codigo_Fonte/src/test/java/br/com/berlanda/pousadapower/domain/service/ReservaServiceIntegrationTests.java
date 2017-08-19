/**
 * 
 */
package br.com.berlanda.pousadapower.domain.service;

import java.util.Calendar;

import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.security.test.context.support.WithUserDetails;

import com.github.springtestdbunit.annotation.DatabaseOperation;
import com.github.springtestdbunit.annotation.DatabaseSetup;

import br.com.berlanda.pousadapower.domain.AbstractIntegrationTests;
import br.com.berlanda.pousadapower.domain.entity.account.Usuario;
import br.com.berlanda.pousadapower.domain.entity.hospedagem.Hospedagem;
import br.com.berlanda.pousadapower.domain.entity.hospedagem.OrcamentoDiaria;
import br.com.berlanda.pousadapower.domain.entity.hospedagem.Responsavel;
import br.com.berlanda.pousadapower.domain.entity.hospedagem.StatusHospedagem;
import br.com.berlanda.pousadapower.domain.entity.hospedagem.TipoHospedagem;
import br.com.berlanda.pousadapower.domain.entity.quarto.StatusQuarto;
import br.com.berlanda.pousadapower.domain.entity.tipoquarto.TipoQuarto;
import br.com.berlanda.pousadapower.domain.entity.tipoquarto.tarifa.Dia;
import br.com.berlanda.pousadapower.domain.entity.tipoquarto.tarifa.TarifaExcecao;
import br.com.berlanda.pousadapower.domain.repository.IQuartoRepository;
import br.com.berlanda.pousadapower.domain.repository.tipoquarto.ITipoQuartoRepository;
import br.com.berlanda.pousadapower.domain.repository.tipoquarto.tarifa.ITarifaExcecaoRepository;

/**
 * @author berlanda
 *
 */
public class ReservaServiceIntegrationTests extends AbstractIntegrationTests
{
	@Autowired
	private ITipoQuartoRepository tipoQuartoRepository;
	
	@Autowired
	private ITarifaExcecaoRepository tarifaExcecaoRepository;
	
	@Autowired
	private IQuartoRepository quartoRepository;
	
	@Autowired
	private ReservaService reservaService;
	
	@Test
	@WithUserDetails("admin@email.com")
	@DatabaseSetup(type = DatabaseOperation.INSERT, value = {
		"/dataset/account/UserDataSet.xml",
		"/dataset/endereco/PaisDataSet.xml",
		"/dataset/endereco/EstadoDataSet.xml",
		"/dataset/endereco/CidadeDataSet.xml",
		"/dataset/fornecedor/FornecedorDataSet.xml",
		"/dataSet/tipoquarto/TipoQuartoDataSet.xml",
		"/dataset/quarto/QuartoDataSet.xml",
		"/dataSet/tipoquarto/tarifa/TarifaExcecaoDataSet.xml",
		"/dataSet/tipoquarto/tarifa/TarifaDataSet.xml",
		"/dataSet/hospedagem/ResponsavelDataSet.xml",
		"/dataSet/hospedagem/HospedagemDataSet.xml",
		"/dataSet/hospedagem/OrcamentoDiariaDataSet.xml",
		"/dataSet/conta/ContaHospedagemDataSet.xml",
		"/dataSet/conta/pagamento/PagamentoDataSet.xml",
		"/dataSet/conta/DiariaHospedagemDataSet.xml",
		"/dataSet/conta/PagamentoHospedagemDataSet.xml",
	})
	public void insertReservaMustPassPlease()
	{
		Hospedagem reserva = new Hospedagem();
		
		Calendar entrada = Calendar.getInstance();
		entrada.set( Calendar.DAY_OF_MONTH, 23 );
		entrada.set( Calendar.MONTH, 11 ); //Calendar Month é 0 based -.-', Oh LORD
		entrada.set( Calendar.YEAR, 2016 );
		
		Calendar saida = Calendar.getInstance();
		saida.set( Calendar.DAY_OF_MONTH, 27 );
		saida.set( Calendar.MONTH, 11 );
		saida.set( Calendar.YEAR, 2016 );
		
		reserva.setEntrada( entrada );
		reserva.setSaida( saida );
		
		reserva.setReservadoPor( new Usuario(1000L) );
		
		Responsavel responsavel = new Responsavel();
		responsavel.setNome( "Responsavel" );
		responsavel.setEmail( "responsavel@mail.com" );
		responsavel.setTelefone( "99999999" );
		
		reserva.setResponsavel( responsavel );
		
		reserva.setStatusHospedagem( StatusHospedagem.RESERVADA );
		reserva.setTipoHospedagem( TipoHospedagem.RESERVA_ANTECIPADA );
		reserva.setTipoQuarto( new TipoQuarto(1000L) );
		
		reserva = this.reservaService.insertReserva( reserva );
		
		Assert.assertNotNull( reserva );
	}

}
