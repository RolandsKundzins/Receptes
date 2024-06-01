package receptes.type;

import java.util.HashMap;
import java.util.Map;

public class OperationResult {
    private boolean success;
    private String message;
    private Map<String, Object> extraData;
    
    // Constructors
    public OperationResult() {
    	extraData = new HashMap<>();
    }

    public OperationResult(boolean success, String message) {
        this.success = success;
        this.message = message;
        extraData = new HashMap<>();
    }
    


    // Getters and setters
    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Map<String, Object> getExtraData() {
        return extraData;
    }

    public void setExtraData(Map<String, Object> extraData) {
        this.extraData = extraData;
    }

    // Method to add extra data to the map
    public void addExtraData(String key, Object value) {
        extraData.put(key, value);
    }

    // Method to retrieve extra data from the map
    public Object getExtraData(String key) {
        return extraData.get(key);
    }
}
