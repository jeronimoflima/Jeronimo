package br.com.GoTrip.rest;

import java.util.HashSet;
import java.util.Set;

import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;

import org.glassfish.jersey.media.multipart.MultiPartFeature;


@ApplicationPath("rest")
public class RestConfig extends Application{
	
	@Override
	public Set<Class<?>> getClasses() {
		Set<Class<?>> resources = new HashSet<Class<?>>();
		
		resources.add(UploadRest.class);
		resources.add(DownloadRest.class);
		resources.add(ExcursaoRest.class);
		resources.add(ParticipanteRest.class);
		resources.add(UsuarioRest.class);
		
		resources.add(MultiPartFeature.class);
		
		return resources;
	}

}
