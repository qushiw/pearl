package com.web.demo.dataStructure;

import com.google.common.collect.Lists;
import com.google.common.util.concurrent.ThreadFactoryBuilder;
import com.lmax.disruptor.*;
import com.lmax.disruptor.dsl.Disruptor;
import com.lmax.disruptor.dsl.ProducerType;
import org.junit.Test;

import java.util.List;
import java.util.concurrent.ThreadFactory;

/**
 * @author jrqushiwen
 * @date 2020/3/29 11:03
 */
public class DisruptorDemo {

    @Test
    public void simpleTest() {
        EventFactory<EntryWrapper> eventFactory = new EventFactory() {
            @Override
            public Object newInstance() {
                return new EntryWrapper();
            }
        };

        ThreadFactory threadFactory = new ThreadFactoryBuilder().setDaemon(false)
                .setNameFormat("disruptor-processors-%d").setUncaughtExceptionHandler(new Thread.UncaughtExceptionHandler() {
            @Override
            public void uncaughtException(Thread t, Throwable e) {
                System.out.println("disruptor handler thread exception");
            }
        }).build();

        Disruptor disruptor = new Disruptor(eventFactory, 512, threadFactory, ProducerType.SINGLE, new SleepingWaitStrategy());
        int size = 2;
        EntryEventHandler[] entryEventHandlers = new EntryEventHandler[size];
        for (int i=0; i<size; i++) {
            entryEventHandlers[i] = new EntryEventHandler(i);
        }
        disruptor.handleEventsWith(entryEventHandlers);
        disruptor.start();


        List<String> entryWrapperList = Lists.newArrayList("1", "2","3");

        RingBuffer<EntryWrapper> ringBuffer = disruptor.getRingBuffer();
        ringBuffer.publishEvents(new EventTranslatorOneArg<EntryWrapper, String>() {
            @Override
            public void translateTo(EntryWrapper event, long sequence, String arg0) {
                event.setEntryValue(arg0);
            }
        }, entryWrapperList.toArray(new String[]{}));
    }


    private class EntryEventHandler implements SequenceReportingEventHandler<EntryWrapper> {
        private int hashcode;
        private Sequence reportingSeq;

        public EntryEventHandler(int hashcode) {
            this.hashcode = hashcode;
        }

        @Override
        public void onEvent(EntryWrapper event, long sequence, boolean endOfBatch) throws Exception {

            System.out.println("entryValue:" + event.getEntryValue() + ", sequence:" + event + ", endOfBatch:" + endOfBatch);

            if (reportingSeq != null) {
                reportingSeq.set(sequence);
            }
        }


        @Override
        public void setSequenceCallback(Sequence sequenceCallback) {
            this.reportingSeq = sequenceCallback;
        }
    }



    private class EntryWrapper {
        private String entryValue;

        public EntryWrapper() {}

        public EntryWrapper(String entryValue) {
            this.entryValue = entryValue;
        }

        public String getEntryValue() {
            return entryValue;
        }

        public void setEntryValue(String entryValue) {
            this.entryValue = entryValue;
        }
    }


}
