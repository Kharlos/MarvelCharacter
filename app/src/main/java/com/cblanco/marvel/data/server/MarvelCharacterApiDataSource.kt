package com.cblanco.marvel.data.server

import com.cblanco.data.source.RemoteDataSource
import com.cblanco.domain.Character
import com.cblanco.marvel.data.mappers.toDomain

class MarvelCharacterApiDataSource(private val marvelApi: MarvelApi) : RemoteDataSource {
    override suspend fun getPublicCharacter(apiKey: String): List<Character> {

        val ts = marvelApi.getTimeStamp()
        val hash = marvelApi.getHash(ts)

        return marvelApi.service.listPublicCharacters(20, 0, apiKey, ts, hash)
            .data?.results?.map {
                it.toDomain()
            } ?: ArrayList()
    }

}