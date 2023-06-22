package proto;

import static io.grpc.MethodDescriptor.generateFullMethodName;
import static io.grpc.stub.ClientCalls.asyncBidiStreamingCall;
import static io.grpc.stub.ClientCalls.asyncClientStreamingCall;
import static io.grpc.stub.ClientCalls.asyncServerStreamingCall;
import static io.grpc.stub.ClientCalls.asyncUnaryCall;
import static io.grpc.stub.ClientCalls.blockingServerStreamingCall;
import static io.grpc.stub.ClientCalls.blockingUnaryCall;
import static io.grpc.stub.ClientCalls.futureUnaryCall;
import static io.grpc.stub.ServerCalls.asyncBidiStreamingCall;
import static io.grpc.stub.ServerCalls.asyncClientStreamingCall;
import static io.grpc.stub.ServerCalls.asyncServerStreamingCall;
import static io.grpc.stub.ServerCalls.asyncUnaryCall;
import static io.grpc.stub.ServerCalls.asyncUnimplementedStreamingCall;
import static io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall;

/**
 */
@javax.annotation.Generated(
    value = "by gRPC proto compiler (version 1.15.0)",
    comments = "Source: esamiOnLine.proto")
public final class GestioneEsamiOnLineGrpc {

  private GestioneEsamiOnLineGrpc() {}

  public static final String SERVICE_NAME = "GestioneEsamiOnLine";

  // Static method descriptors that strictly reflect the proto.
  private static volatile io.grpc.MethodDescriptor<proto.EsamiOnLine.Empty,
      proto.EsamiOnLine.Appelli> getGetAppelliMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "getAppelli",
      requestType = proto.EsamiOnLine.Empty.class,
      responseType = proto.EsamiOnLine.Appelli.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<proto.EsamiOnLine.Empty,
      proto.EsamiOnLine.Appelli> getGetAppelliMethod() {
    io.grpc.MethodDescriptor<proto.EsamiOnLine.Empty, proto.EsamiOnLine.Appelli> getGetAppelliMethod;
    if ((getGetAppelliMethod = GestioneEsamiOnLineGrpc.getGetAppelliMethod) == null) {
      synchronized (GestioneEsamiOnLineGrpc.class) {
        if ((getGetAppelliMethod = GestioneEsamiOnLineGrpc.getGetAppelliMethod) == null) {
          GestioneEsamiOnLineGrpc.getGetAppelliMethod = getGetAppelliMethod = 
              io.grpc.MethodDescriptor.<proto.EsamiOnLine.Empty, proto.EsamiOnLine.Appelli>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(
                  "GestioneEsamiOnLine", "getAppelli"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  proto.EsamiOnLine.Empty.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  proto.EsamiOnLine.Appelli.getDefaultInstance()))
                  .setSchemaDescriptor(new GestioneEsamiOnLineMethodDescriptorSupplier("getAppelli"))
                  .build();
          }
        }
     }
     return getGetAppelliMethod;
  }

  private static volatile io.grpc.MethodDescriptor<proto.EsamiOnLine.Prenotazione,
      proto.EsamiOnLine.Esito> getSetPrenotazioneMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "setPrenotazione",
      requestType = proto.EsamiOnLine.Prenotazione.class,
      responseType = proto.EsamiOnLine.Esito.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<proto.EsamiOnLine.Prenotazione,
      proto.EsamiOnLine.Esito> getSetPrenotazioneMethod() {
    io.grpc.MethodDescriptor<proto.EsamiOnLine.Prenotazione, proto.EsamiOnLine.Esito> getSetPrenotazioneMethod;
    if ((getSetPrenotazioneMethod = GestioneEsamiOnLineGrpc.getSetPrenotazioneMethod) == null) {
      synchronized (GestioneEsamiOnLineGrpc.class) {
        if ((getSetPrenotazioneMethod = GestioneEsamiOnLineGrpc.getSetPrenotazioneMethod) == null) {
          GestioneEsamiOnLineGrpc.getSetPrenotazioneMethod = getSetPrenotazioneMethod = 
              io.grpc.MethodDescriptor.<proto.EsamiOnLine.Prenotazione, proto.EsamiOnLine.Esito>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(
                  "GestioneEsamiOnLine", "setPrenotazione"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  proto.EsamiOnLine.Prenotazione.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  proto.EsamiOnLine.Esito.getDefaultInstance()))
                  .setSchemaDescriptor(new GestioneEsamiOnLineMethodDescriptorSupplier("setPrenotazione"))
                  .build();
          }
        }
     }
     return getSetPrenotazioneMethod;
  }

  private static volatile io.grpc.MethodDescriptor<proto.EsamiOnLine.Prenotazione,
      proto.EsamiOnLine.Domande> getGetDomandeMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "getDomande",
      requestType = proto.EsamiOnLine.Prenotazione.class,
      responseType = proto.EsamiOnLine.Domande.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<proto.EsamiOnLine.Prenotazione,
      proto.EsamiOnLine.Domande> getGetDomandeMethod() {
    io.grpc.MethodDescriptor<proto.EsamiOnLine.Prenotazione, proto.EsamiOnLine.Domande> getGetDomandeMethod;
    if ((getGetDomandeMethod = GestioneEsamiOnLineGrpc.getGetDomandeMethod) == null) {
      synchronized (GestioneEsamiOnLineGrpc.class) {
        if ((getGetDomandeMethod = GestioneEsamiOnLineGrpc.getGetDomandeMethod) == null) {
          GestioneEsamiOnLineGrpc.getGetDomandeMethod = getGetDomandeMethod = 
              io.grpc.MethodDescriptor.<proto.EsamiOnLine.Prenotazione, proto.EsamiOnLine.Domande>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(
                  "GestioneEsamiOnLine", "getDomande"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  proto.EsamiOnLine.Prenotazione.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  proto.EsamiOnLine.Domande.getDefaultInstance()))
                  .setSchemaDescriptor(new GestioneEsamiOnLineMethodDescriptorSupplier("getDomande"))
                  .build();
          }
        }
     }
     return getGetDomandeMethod;
  }

  private static volatile io.grpc.MethodDescriptor<proto.EsamiOnLine.Sottomissione,
      proto.EsamiOnLine.Modulo> getSetRisposteMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "setRisposte",
      requestType = proto.EsamiOnLine.Sottomissione.class,
      responseType = proto.EsamiOnLine.Modulo.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<proto.EsamiOnLine.Sottomissione,
      proto.EsamiOnLine.Modulo> getSetRisposteMethod() {
    io.grpc.MethodDescriptor<proto.EsamiOnLine.Sottomissione, proto.EsamiOnLine.Modulo> getSetRisposteMethod;
    if ((getSetRisposteMethod = GestioneEsamiOnLineGrpc.getSetRisposteMethod) == null) {
      synchronized (GestioneEsamiOnLineGrpc.class) {
        if ((getSetRisposteMethod = GestioneEsamiOnLineGrpc.getSetRisposteMethod) == null) {
          GestioneEsamiOnLineGrpc.getSetRisposteMethod = getSetRisposteMethod = 
              io.grpc.MethodDescriptor.<proto.EsamiOnLine.Sottomissione, proto.EsamiOnLine.Modulo>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(
                  "GestioneEsamiOnLine", "setRisposte"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  proto.EsamiOnLine.Sottomissione.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  proto.EsamiOnLine.Modulo.getDefaultInstance()))
                  .setSchemaDescriptor(new GestioneEsamiOnLineMethodDescriptorSupplier("setRisposte"))
                  .build();
          }
        }
     }
     return getSetRisposteMethod;
  }

  /**
   * Creates a new async stub that supports all call types for the service
   */
  public static GestioneEsamiOnLineStub newStub(io.grpc.Channel channel) {
    return new GestioneEsamiOnLineStub(channel);
  }

  /**
   * Creates a new blocking-style stub that supports unary and streaming output calls on the service
   */
  public static GestioneEsamiOnLineBlockingStub newBlockingStub(
      io.grpc.Channel channel) {
    return new GestioneEsamiOnLineBlockingStub(channel);
  }

  /**
   * Creates a new ListenableFuture-style stub that supports unary calls on the service
   */
  public static GestioneEsamiOnLineFutureStub newFutureStub(
      io.grpc.Channel channel) {
    return new GestioneEsamiOnLineFutureStub(channel);
  }

  /**
   */
  public static abstract class GestioneEsamiOnLineImplBase implements io.grpc.BindableService {

    /**
     */
    public void getAppelli(proto.EsamiOnLine.Empty request,
        io.grpc.stub.StreamObserver<proto.EsamiOnLine.Appelli> responseObserver) {
      asyncUnimplementedUnaryCall(getGetAppelliMethod(), responseObserver);
    }

    /**
     */
    public void setPrenotazione(proto.EsamiOnLine.Prenotazione request,
        io.grpc.stub.StreamObserver<proto.EsamiOnLine.Esito> responseObserver) {
      asyncUnimplementedUnaryCall(getSetPrenotazioneMethod(), responseObserver);
    }

    /**
     */
    public void getDomande(proto.EsamiOnLine.Prenotazione request,
        io.grpc.stub.StreamObserver<proto.EsamiOnLine.Domande> responseObserver) {
      asyncUnimplementedUnaryCall(getGetDomandeMethod(), responseObserver);
    }

    /**
     */
    public void setRisposte(proto.EsamiOnLine.Sottomissione request,
        io.grpc.stub.StreamObserver<proto.EsamiOnLine.Modulo> responseObserver) {
      asyncUnimplementedUnaryCall(getSetRisposteMethod(), responseObserver);
    }

    @java.lang.Override public final io.grpc.ServerServiceDefinition bindService() {
      return io.grpc.ServerServiceDefinition.builder(getServiceDescriptor())
          .addMethod(
            getGetAppelliMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                proto.EsamiOnLine.Empty,
                proto.EsamiOnLine.Appelli>(
                  this, METHODID_GET_APPELLI)))
          .addMethod(
            getSetPrenotazioneMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                proto.EsamiOnLine.Prenotazione,
                proto.EsamiOnLine.Esito>(
                  this, METHODID_SET_PRENOTAZIONE)))
          .addMethod(
            getGetDomandeMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                proto.EsamiOnLine.Prenotazione,
                proto.EsamiOnLine.Domande>(
                  this, METHODID_GET_DOMANDE)))
          .addMethod(
            getSetRisposteMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                proto.EsamiOnLine.Sottomissione,
                proto.EsamiOnLine.Modulo>(
                  this, METHODID_SET_RISPOSTE)))
          .build();
    }
  }

  /**
   */
  public static final class GestioneEsamiOnLineStub extends io.grpc.stub.AbstractStub<GestioneEsamiOnLineStub> {
    private GestioneEsamiOnLineStub(io.grpc.Channel channel) {
      super(channel);
    }

    private GestioneEsamiOnLineStub(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected GestioneEsamiOnLineStub build(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      return new GestioneEsamiOnLineStub(channel, callOptions);
    }

    /**
     */
    public void getAppelli(proto.EsamiOnLine.Empty request,
        io.grpc.stub.StreamObserver<proto.EsamiOnLine.Appelli> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getGetAppelliMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     */
    public void setPrenotazione(proto.EsamiOnLine.Prenotazione request,
        io.grpc.stub.StreamObserver<proto.EsamiOnLine.Esito> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getSetPrenotazioneMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     */
    public void getDomande(proto.EsamiOnLine.Prenotazione request,
        io.grpc.stub.StreamObserver<proto.EsamiOnLine.Domande> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getGetDomandeMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     */
    public void setRisposte(proto.EsamiOnLine.Sottomissione request,
        io.grpc.stub.StreamObserver<proto.EsamiOnLine.Modulo> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getSetRisposteMethod(), getCallOptions()), request, responseObserver);
    }
  }

  /**
   */
  public static final class GestioneEsamiOnLineBlockingStub extends io.grpc.stub.AbstractStub<GestioneEsamiOnLineBlockingStub> {
    private GestioneEsamiOnLineBlockingStub(io.grpc.Channel channel) {
      super(channel);
    }

    private GestioneEsamiOnLineBlockingStub(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected GestioneEsamiOnLineBlockingStub build(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      return new GestioneEsamiOnLineBlockingStub(channel, callOptions);
    }

    /**
     */
    public proto.EsamiOnLine.Appelli getAppelli(proto.EsamiOnLine.Empty request) {
      return blockingUnaryCall(
          getChannel(), getGetAppelliMethod(), getCallOptions(), request);
    }

    /**
     */
    public proto.EsamiOnLine.Esito setPrenotazione(proto.EsamiOnLine.Prenotazione request) {
      return blockingUnaryCall(
          getChannel(), getSetPrenotazioneMethod(), getCallOptions(), request);
    }

    /**
     */
    public proto.EsamiOnLine.Domande getDomande(proto.EsamiOnLine.Prenotazione request) {
      return blockingUnaryCall(
          getChannel(), getGetDomandeMethod(), getCallOptions(), request);
    }

    /**
     */
    public proto.EsamiOnLine.Modulo setRisposte(proto.EsamiOnLine.Sottomissione request) {
      return blockingUnaryCall(
          getChannel(), getSetRisposteMethod(), getCallOptions(), request);
    }
  }

  /**
   */
  public static final class GestioneEsamiOnLineFutureStub extends io.grpc.stub.AbstractStub<GestioneEsamiOnLineFutureStub> {
    private GestioneEsamiOnLineFutureStub(io.grpc.Channel channel) {
      super(channel);
    }

    private GestioneEsamiOnLineFutureStub(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected GestioneEsamiOnLineFutureStub build(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      return new GestioneEsamiOnLineFutureStub(channel, callOptions);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<proto.EsamiOnLine.Appelli> getAppelli(
        proto.EsamiOnLine.Empty request) {
      return futureUnaryCall(
          getChannel().newCall(getGetAppelliMethod(), getCallOptions()), request);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<proto.EsamiOnLine.Esito> setPrenotazione(
        proto.EsamiOnLine.Prenotazione request) {
      return futureUnaryCall(
          getChannel().newCall(getSetPrenotazioneMethod(), getCallOptions()), request);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<proto.EsamiOnLine.Domande> getDomande(
        proto.EsamiOnLine.Prenotazione request) {
      return futureUnaryCall(
          getChannel().newCall(getGetDomandeMethod(), getCallOptions()), request);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<proto.EsamiOnLine.Modulo> setRisposte(
        proto.EsamiOnLine.Sottomissione request) {
      return futureUnaryCall(
          getChannel().newCall(getSetRisposteMethod(), getCallOptions()), request);
    }
  }

  private static final int METHODID_GET_APPELLI = 0;
  private static final int METHODID_SET_PRENOTAZIONE = 1;
  private static final int METHODID_GET_DOMANDE = 2;
  private static final int METHODID_SET_RISPOSTE = 3;

  private static final class MethodHandlers<Req, Resp> implements
      io.grpc.stub.ServerCalls.UnaryMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.ServerStreamingMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.ClientStreamingMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.BidiStreamingMethod<Req, Resp> {
    private final GestioneEsamiOnLineImplBase serviceImpl;
    private final int methodId;

    MethodHandlers(GestioneEsamiOnLineImplBase serviceImpl, int methodId) {
      this.serviceImpl = serviceImpl;
      this.methodId = methodId;
    }

    @java.lang.Override
    @java.lang.SuppressWarnings("unchecked")
    public void invoke(Req request, io.grpc.stub.StreamObserver<Resp> responseObserver) {
      switch (methodId) {
        case METHODID_GET_APPELLI:
          serviceImpl.getAppelli((proto.EsamiOnLine.Empty) request,
              (io.grpc.stub.StreamObserver<proto.EsamiOnLine.Appelli>) responseObserver);
          break;
        case METHODID_SET_PRENOTAZIONE:
          serviceImpl.setPrenotazione((proto.EsamiOnLine.Prenotazione) request,
              (io.grpc.stub.StreamObserver<proto.EsamiOnLine.Esito>) responseObserver);
          break;
        case METHODID_GET_DOMANDE:
          serviceImpl.getDomande((proto.EsamiOnLine.Prenotazione) request,
              (io.grpc.stub.StreamObserver<proto.EsamiOnLine.Domande>) responseObserver);
          break;
        case METHODID_SET_RISPOSTE:
          serviceImpl.setRisposte((proto.EsamiOnLine.Sottomissione) request,
              (io.grpc.stub.StreamObserver<proto.EsamiOnLine.Modulo>) responseObserver);
          break;
        default:
          throw new AssertionError();
      }
    }

    @java.lang.Override
    @java.lang.SuppressWarnings("unchecked")
    public io.grpc.stub.StreamObserver<Req> invoke(
        io.grpc.stub.StreamObserver<Resp> responseObserver) {
      switch (methodId) {
        default:
          throw new AssertionError();
      }
    }
  }

  private static abstract class GestioneEsamiOnLineBaseDescriptorSupplier
      implements io.grpc.protobuf.ProtoFileDescriptorSupplier, io.grpc.protobuf.ProtoServiceDescriptorSupplier {
    GestioneEsamiOnLineBaseDescriptorSupplier() {}

    @java.lang.Override
    public com.google.protobuf.Descriptors.FileDescriptor getFileDescriptor() {
      return proto.EsamiOnLine.getDescriptor();
    }

    @java.lang.Override
    public com.google.protobuf.Descriptors.ServiceDescriptor getServiceDescriptor() {
      return getFileDescriptor().findServiceByName("GestioneEsamiOnLine");
    }
  }

  private static final class GestioneEsamiOnLineFileDescriptorSupplier
      extends GestioneEsamiOnLineBaseDescriptorSupplier {
    GestioneEsamiOnLineFileDescriptorSupplier() {}
  }

  private static final class GestioneEsamiOnLineMethodDescriptorSupplier
      extends GestioneEsamiOnLineBaseDescriptorSupplier
      implements io.grpc.protobuf.ProtoMethodDescriptorSupplier {
    private final String methodName;

    GestioneEsamiOnLineMethodDescriptorSupplier(String methodName) {
      this.methodName = methodName;
    }

    @java.lang.Override
    public com.google.protobuf.Descriptors.MethodDescriptor getMethodDescriptor() {
      return getServiceDescriptor().findMethodByName(methodName);
    }
  }

  private static volatile io.grpc.ServiceDescriptor serviceDescriptor;

  public static io.grpc.ServiceDescriptor getServiceDescriptor() {
    io.grpc.ServiceDescriptor result = serviceDescriptor;
    if (result == null) {
      synchronized (GestioneEsamiOnLineGrpc.class) {
        result = serviceDescriptor;
        if (result == null) {
          serviceDescriptor = result = io.grpc.ServiceDescriptor.newBuilder(SERVICE_NAME)
              .setSchemaDescriptor(new GestioneEsamiOnLineFileDescriptorSupplier())
              .addMethod(getGetAppelliMethod())
              .addMethod(getSetPrenotazioneMethod())
              .addMethod(getGetDomandeMethod())
              .addMethod(getSetRisposteMethod())
              .build();
        }
      }
    }
    return result;
  }
}
