package ch.heigvd.res.stpatrick;

import java.io.*;

/**
 * This class is responsible for providing different types of Stream Processors.
 * 
 * @author Olivier Liechti
 */
public class StreamProcessorsFactory implements IStreamProcessorsFactory {

  @Override
  public IStreamProcessor getProcessor() {
    return new BasicStreamProcessor();
  }

  @Override
  public IStreamProcessor getProcessor(String processorName) throws UnknownNameException {
    if(processorName == "e-remover") {
      return new IStreamProcessor() {
        @Override
        public void process(Reader in, Writer out) throws IOException {
          BufferedReader br = new BufferedReader(in);
          BufferedWriter bw = new BufferedWriter(out);
          int c = br.read();
          while (c != -1) {
            if(new Character('e').compareTo((char) c) != 0) {
              bw.write(c);
            }
            c = br.read();
          }
          bw.flush();
        }
      };
    }
    else {
      throw new UnknownNameException("The factory does not know any processor called " + processorName);
    }
  }

}
