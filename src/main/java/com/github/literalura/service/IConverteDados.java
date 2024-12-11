package com.github.literalura.service;

public interface IConverteDados {
    <T> Object obterDados(String json, Class<T> classe);
}
