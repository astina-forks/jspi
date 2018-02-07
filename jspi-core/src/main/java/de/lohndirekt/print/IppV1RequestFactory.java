package de.lohndirekt.print;

import de.lohndirekt.print.attribute.AttributeWriter;
import de.lohndirekt.print.attribute.auth.RequestingUserPassword;
import de.lohndirekt.print.attribute.ipp.printerdesc.supported.OperationsSupported;

import javax.print.attribute.standard.RequestingUserName;
import java.io.ByteArrayOutputStream;
import java.net.URI;

/**
 * Creates requests with an IPP 1.0 header. You're responsible for not sending
 * anything an IPP 1.0 server wouldn't understand!
 */
public class IppV1RequestFactory implements IppRequestFactory
{
    public IppRequest createIppRequest(URI uri, OperationsSupported operation, RequestingUserName user, RequestingUserPassword passwd)
    {
        return new IppRequestCupsImpl(uri, operation)
        {
            @Override
            byte[] ippHeader(OperationsSupported operation, int id)
            {
                //Ipp header data according to http://www.ietf.org/rfc/rfc2910.txt
                ByteArrayOutputStream out = new ByteArrayOutputStream(8);
                //The first 2 bytes represent the IPP version number (1.0)
                //major version-number
                out.write((byte) 1);
                //minor version-number
                out.write((byte) 0);
                //2 byte operation id
                AttributeWriter.writeInt2(operation.getValue(), out);
                //4 byte request id
                AttributeWriter.writeInt4(id, out);
                return out.toByteArray();
            }
        };
    }
}
