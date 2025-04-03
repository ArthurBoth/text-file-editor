package io;

public enum PartitionUnit {   
    KB {
        @Override
        public long getBytes(int size) {
            return (long) size << 10;
        }
    }, 
    MB {
        @Override
        public long getBytes(int size) {
            return (long) size << 20;
        }
    }, 
    GB {
        @Override
        public long getBytes(int size) {
            return (long) size << 30;
        }
    };

    public abstract long getBytes(int size);
}
