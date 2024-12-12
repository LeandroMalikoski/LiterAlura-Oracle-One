package com.github.literalura.service;

import com.github.literalura.model.DadosBusca;

public interface IConverteDados {
    <T> T obterDados(String json, Class<T> classe);
}