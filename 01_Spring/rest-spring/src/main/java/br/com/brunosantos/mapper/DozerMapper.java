package br.com.brunosantos.mapper;

import java.util.ArrayList;
import java.util.List;

import com.github.dozermapper.core.DozerBeanMapperBuilder;
import com.github.dozermapper.core.Mapper;

public class DozerMapper {

	private static Mapper mapper = DozerBeanMapperBuilder.buildDefault();
	
	public static <O, D> D parseObject(O origen, Class<D> destino){
		return mapper.map(origen, destino);
	}
	
	public static <O, D> List<D> parseListObjects(List<O> origen, Class<D> destino){
		List<D> destinoObjects = new ArrayList<>();
		for (O o : origen) {
			destinoObjects.add(mapper.map(o, destino));
		}
		return destinoObjects;
	}
}
