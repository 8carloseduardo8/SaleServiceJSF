package com.util;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;

public class Read {
	public static byte[] readBytes(InputStream stream) throws IOException {
		if (stream == null)
			return new byte[] {};
		byte[] buffer = new byte[1024];
		ByteArrayOutputStream output = new ByteArrayOutputStream();
		boolean error = false;
		try {
			int numRead = 0;
			while ((numRead = stream.read(buffer)) > -1) {
				output.write(buffer, 0, numRead);
			}
		} catch (IOException e) {
			error = true; // this error should be thrown, even if there is an
							// error closing stream
			throw e;
		} catch (RuntimeException e) {
			error = true; // this error should be thrown, even if there is an
							// error closing stream
			throw e;
		} finally {
			try {
				stream.close();
			} catch (IOException e) {
				if (!error)
					throw e;
			}
		}
		output.flush();
		return output.toByteArray();
	}
}
