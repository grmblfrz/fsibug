package com.example.fsibug;

import com.github.robtimus.filesystems.ftp.ConnectionMode;
import com.github.robtimus.filesystems.ftp.FTPEnvironment;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Component;

import java.net.URI;
import java.nio.file.FileSystem;
import java.nio.file.FileSystems;
import java.nio.file.spi.FileSystemProvider;

@SpringBootApplication
public class FsibugApplication {

	public static void main(String[] args) {
		SpringApplication.run(FsibugApplication.class, args);
	}
}

@Component
class FsibugRunner implements ApplicationRunner {
	@Override
	public void run(ApplicationArguments args) throws Exception {
		for (FileSystemProvider provider : FileSystemProvider.installedProviders()) {
			System.out.println("found provider for scheme " + provider.getScheme());
		}
		FTPEnvironment ftpEnvironment = new FTPEnvironment().withConnectionMode(ConnectionMode.PASSIVE);
		System.out.println("created ftpEnvironment");
		try (FileSystem fs = FileSystems.newFileSystem(URI.create("ftp://ftp:email@ftp.scene.org"), ftpEnvironment))
		{
			System.out.println("created ftp filesystem " + fs.getClass().getName());
		}
	}
}
