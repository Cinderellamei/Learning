/*
package Algorithm.leecode.test.json;

import com.alibaba.fastjson.JSONObject;

import com.alibaba.fastjson.JSONArray;

public class JsonCompareTest {
    public static boolean jsonsEqual(Object obj1, Object obj2) throws JSONException

    {
        if (!obj1.getClass().equals(obj2.getClass()))
        {
            return false;
        }

        if (obj1 instanceof JSONObject)
        {
            JSONObject jsonObj1 = (JSONObject) obj1;

            JSONObject jsonObj2 = (JSONObject) obj2;

            String[] names = JSONObject.getNames(jsonObj1);
            String[] names2 = JSONObject.getNames(jsonObj1);
            if (names.length != names2.length)
            {
                return false;
            }

            for (String fieldName:names)
            {
                Object obj1FieldValue = jsonObj1.get(fieldName);

                Object obj2FieldValue = jsonObj2.get(fieldName);

                if (!jsonsEqual(obj1FieldValue, obj2FieldValue))
                {
                    return false;
                }
            }
        }
        else if (obj1 instanceof JSONArray)
        {
            JSONArray obj1Array = (JSONArray) obj1;
            JSONArray obj2Array = (JSONArray) obj2;

            if (obj1Array.length() != obj2Array.length())
            {
                return false;
            }

            for (int i = 0; i < obj1Array.length(); i++)
            {
                boolean matchFound = false;

                for (int j = 0; j < obj2Array.length(); j++)
                {
                    if (jsonsEqual(obj1Array.get(i), obj2Array.get(j)))
                    {
                        matchFound = true;
                        break;
                    }
                }

                if (!matchFound)
                {
                    return false;
                }
            }
        }
        else
        {
            if (!obj1.equals(obj2))
            {
                return false;
            }
        }

        return true;
    }
}
*/
