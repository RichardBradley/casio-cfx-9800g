package org.bradders.casiocfx9800g.ui;

import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

import org.junit.Test;

public class AppletUserInterfaceTest
{
   /**
    * Verify that the newSingleThreadExecutor spawns a single worker thread,
    * separate from the calling thread.
    * @throws Exception 
    */
   @Test
   public void testSingleThreadExecutor() throws Exception
   {
      ExecutorService executor = Executors.newSingleThreadExecutor();
      final ThreadLocal<Integer> threadLocal = new ThreadLocal<Integer>();
      
      threadLocal.set(1);
      executor.execute(new Runnable() {
         @Override
         public void run()
         {
            threadLocal.set(99);
         }
      });
      
      assertThat(threadLocal.get(), equalTo(1));
      Integer valOnWorkerThread = executor.submit(new Callable<Integer>() {
         @Override
         public Integer call() throws Exception
         {
            return threadLocal.get();
         }
      }).get();
      assertThat(valOnWorkerThread, equalTo(99));
   }

   @Test
   public void testBlockingQueue() throws Exception
   {
      ExecutorService executor = Executors.newSingleThreadExecutor();
      final ArrayBlockingQueue<String> queue = new ArrayBlockingQueue<String>(1);
      
      Future<String> fetchFuture = executor.submit(new Callable<String>() {
         @Override
         public String call() throws Exception
         {
            return queue.take();
         }
      });
      
      assertThat(fetchFuture.isDone(), is(false));
      
      // todo: detect if queue has any listeners
      queue.add("element");
      
      assertThat(fetchFuture.get(), equalTo("element"));
   }
}
