　因为BlockingQueue是一个阻塞队列，它的存取可以保证只有一个线程在进行，所以根据逻辑，生产者在内存满的时候进行等待，并且唤醒消费者队列，反过来消费者在饥饿状态下等待并唤醒生产者进行生产。