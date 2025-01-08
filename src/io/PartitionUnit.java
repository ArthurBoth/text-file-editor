package io;

public enum PartitionUnit {   
    KB {
        @Override
        public long getBytes(long size) {
            return size << 10;
        }
    }, 
    MB {
        @Override
        public long getBytes(long size) {
            return size << 20;
        }
    }, 
    GB {
        @Override
        public long getBytes(long size) {
            return size << 30;
        }
    };

    public abstract long getBytes(long size);
}
