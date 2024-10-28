package io;

public enum PartitionUnit {   
    KB {
        @Override
        public long getBytes(long size) {
            return size * 1024;
        }
    }, 
    MB {
        @Override
        public long getBytes(long size) {
            return size * 1024 * 1024;
        }
    }, 
    GB {
        @Override
        public long getBytes(long size) {
            return size * 1024 * 1024 * 1024;
        }
    };

    public abstract long getBytes(long size);
}
