package com.justica.processo.service;

import com.justica.processo.converter.PessoaConverter;
import com.justica.processo.exception.ObjetoNaoEncontradoException;
import com.justica.processo.exception.PessoaDuplicadoException;
import com.justica.processo.model.Pessoa;
import com.justica.processo.model.PessoaFisica;
import com.justica.processo.model.PessoaJuridica;
import com.justica.processo.model.domain.DominioStatusPessoa;
import com.justica.processo.model.domain.DominioTipoPessoa;
import com.justica.processo.model.dto.rest.PessoaDTO;
import com.justica.processo.repository.PessoaRepository;
import lombok.extern.log4j.Log4j2;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import javax.transaction.Transactional;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.Optional;

import static com.justica.processo.constants.MensagemConstants.MSG_SERVICE_PESSOA_NOT_EMPTY;

@Log4j2
@Service
@Validated
public class PessoaService extends GenericService<PessoaRepository, Pessoa, String> {
    private final ModelMapper modelMapper;
    private final PessoaConverter pessoaConverter;

    @Autowired
    public PessoaService(PessoaRepository repository, ModelMapper modelMapper, PessoaConverter pessoaConverter) {
        super(repository);
        this.modelMapper = modelMapper;
        this.pessoaConverter = pessoaConverter;
    }

    public PessoaDTO consultarPessoa(@NotNull(message = MSG_SERVICE_PESSOA_NOT_EMPTY) PessoaDTO pessoaDTO) {
        log.info("Iniciando consulta da pessoa [{}]...", pessoaDTO.getNumeroDocumento());
            Optional<Pessoa> optionalPessoa = this.repository.findByNumeroDocumentoAndTipoPessoaAndStatusPessoa(pessoaDTO.getNumeroDocumento(),
                    pessoaDTO.getTipoPessoa(), DominioStatusPessoa.ATIVO);

        log.info("Finalizando consulta da pessoa [{}]...", pessoaDTO.getNumeroDocumento());

        return this.pessoaConverter.converterEntidadeParaResponseDTO(optionalPessoa.get());
    }

    @Transactional
    public PessoaDTO incluirPessoa(@NotNull(message = "service.pessoa.notnull.message") PessoaDTO pessoaDTO){

        log.info("Iniciando inclusão da pessoa [{}]...", pessoaDTO.getNumeroDocumento());

        if (this.existePessoa(pessoaDTO.getNumeroDocumento(), DominioTipoPessoa.FISICA, DominioStatusPessoa.ATIVO)) {
            throw new PessoaDuplicadoException(pessoaDTO.getNumeroDocumento());
        }

        log.info("Pessoa [{}] incluída", pessoaDTO.getNumeroDocumento());

        return salvarPessoa(pessoaDTO);
    }

    @Transactional
    public PessoaDTO alterarPessoa(@NotNull(message = "service.pessoa.notnull.message") PessoaDTO pessoaDTO) {
        log.info("Iniciando alteração da pessoa [{}]...", pessoaDTO.getNumeroDocumento());

        if (this.existePessoa(pessoaDTO.getNumeroDocumento(), pessoaDTO.getTipoPessoa())) {

            Pessoa pessoa =  this.repository.findByNumeroDocumentoAndTipoPessoaAndStatusPessoa(pessoaDTO.getNumeroDocumento(),
                    pessoaDTO.getTipoPessoa(), DominioStatusPessoa.ATIVO).orElseThrow(() ->
                    new ObjetoNaoEncontradoException("service.pessoa.notnull.message", pessoaDTO.getNumeroDocumento()));

            pessoa.setStatusPessoa(DominioStatusPessoa.INATIVO);
            super.repository.save(pessoa);

        } else {
            throw new ObjetoNaoEncontradoException("service.pessoa.notnull.message", pessoaDTO.getNumeroDocumento());
        }

        return salvarPessoa(pessoaDTO);
    }

    @Transactional
    public PessoaDTO excluirPessoa(@NotNull(message = "service.pessoa.notnull.message")  PessoaDTO pessoaDTO){
        log.info("Iniciando exclusão da pessoa [{}]...", pessoaDTO.getNumeroDocumento());

        Pessoa pessoa =  this.repository.findByNumeroDocumentoAndTipoPessoaAndStatusPessoa(pessoaDTO.getNumeroDocumento(),
                pessoaDTO.getTipoPessoa(), DominioStatusPessoa.ATIVO).orElseThrow(() ->
                new ObjetoNaoEncontradoException("service.pessoa.notnull.message", pessoaDTO.getNumeroDocumento()));

        log.info("Pessoa [{}] excluída", pessoaDTO.getNumeroDocumento());
        return this.atualizaSituacaoPessoa(pessoa, DominioStatusPessoa.EXCLUIDO);
    }

    private PessoaDTO salvarPessoa(@NotNull(message = "service.pessoa.notnull.message") PessoaDTO pessoaDTO) {
        if (pessoaDTO.getTipoPessoa().equals(DominioTipoPessoa.FISICA)){
            Pessoa pessoa = this.pessoaConverter.converterDTOParaPFEntidade(pessoaDTO);
            pessoa.setStatusPessoa(DominioStatusPessoa.ATIVO);
            pessoa.setTipoPessoa(DominioTipoPessoa.FISICA);
            final PessoaFisica pessoaFisica = (PessoaFisica) super.repository.save(pessoa);
            return this.pessoaConverter.converterEntidadeParaPFResponseDTO(pessoaFisica);}
        else {      Pessoa pessoa = this.pessoaConverter.converterDTOParaPJEntidade(pessoaDTO);
            pessoa.setStatusPessoa(DominioStatusPessoa.ATIVO);
            pessoa.setTipoPessoa(DominioTipoPessoa.JURIDICA);
            final PessoaJuridica pessoaJuridica = (PessoaJuridica) super.repository.save(pessoa);
            return this.pessoaConverter.converterEntidadeParaPJResponseDTO(pessoaJuridica);}
    }

    private PessoaDTO atualizaSituacaoPessoa(Pessoa pessoa, DominioStatusPessoa statusPessoa) {
        log.info("Atualizando do status da pessoa [{}] para [{}]", pessoa.getNumeroDocumento(), statusPessoa);
        pessoa.setStatusPessoa(statusPessoa);
        log.debug("Atualização do status da pessoa realizado com sucesso...");
        return this.pessoaConverter.converterEntidadeParaResponseDTO(super.repository.save(pessoa));
    }

    public boolean existePessoa(@NotEmpty(message = MSG_SERVICE_PESSOA_NOT_EMPTY) String cpfcnpj, DominioTipoPessoa tipoPessoa) {
        return super.repository.existsByNumeroDocumentoAndTipoPessoa(cpfcnpj, tipoPessoa);
    }

    public boolean existePessoa(@NotEmpty(message = MSG_SERVICE_PESSOA_NOT_EMPTY) String cpfcnpj, DominioTipoPessoa tipoPessoa, DominioStatusPessoa statusPessoa) {
        return super.repository.existsByNumeroDocumentoAndTipoPessoaAndStatusPessoa(cpfcnpj, tipoPessoa, statusPessoa);
    }

}
