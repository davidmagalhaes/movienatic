package com.davidmag.movienatic.data.source.local.impl

import com.davidmag.movienatic.data.source.boundary.local.ImageConfigsLocalDatasource
import com.davidmag.movienatic.data.source.local.dto.ImageConfigsDb
import com.davidmag.movienatic.data.source.local.mapper.ImageConfigsLocalMapper
import com.davidmag.movienatic.domain.model.ImageConfigs
import io.reactivex.Flowable
import io.reactivex.Maybe
import io.realm.Realm

class ImageConfigsLocalDatasourceImpl : ImageConfigsLocalDatasource {

    val realm = Realm.getDefaultInstance()

    override fun cache(configs: ImageConfigs): Maybe<*> {
        return Maybe.fromCallable {
            realm.executeTransaction {
                it.copyToRealmOrUpdate(ImageConfigsLocalMapper.toDto(configs))
            }
        }
    }

    override fun get(): Flowable<List<ImageConfigs>> {
        return realm.where(ImageConfigsDb::class.java).findAllAsync().
            asFlowable().map {
            ImageConfigsLocalMapper.toEntity(it)
        }
    }
}