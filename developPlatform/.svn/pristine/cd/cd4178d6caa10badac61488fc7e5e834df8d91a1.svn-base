package com.tengzhi.business.system.core.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.Objects;

@Entity
@Table(name = "sys_gen_note")
@IdClass(value = SysGenNote.SysGenNoteRolePK.class)
public class SysGenNote {
    @Id
    private String prefix;
    @Id
    private String dateFormat;
    @Id
    private String date;
    @Id
    private String tableName;
    @Id
    private String corpId;

    private Long increment;
    private Integer length;

    public String getPrefix() {
        return prefix;
    }

    public void setPrefix(String prefix) {
        this.prefix = prefix;
    }

    public String getDateFormat() {
        return dateFormat;
    }

    public void setDateFormat(String dateFormat) {
        this.dateFormat = dateFormat;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getTableName() {
        return tableName;
    }

    public void setTableName(String tableName) {
        this.tableName = tableName;
    }

    public String getCorpId() {
        return corpId;
    }

    public void setCorpId(String corpId) {
        this.corpId = corpId;
    }

    public Long getIncrement() {
        return increment;
    }

    public void setIncrement(Long increment) {
        this.increment = increment;
    }

    public Integer getLength() {
        return length;
    }

    public void setLength(Integer length) {
        this.length = length;
    }

    public static class SysGenNoteRolePK implements Serializable {
        private String prefix;
        private String dateFormat;
        private String date;
        private String tableName;
        private String corpId;

        public SysGenNoteRolePK() {

        }

        public SysGenNoteRolePK(String prefix, String dateFormat, String date, String tableName, String corpId) {
            this.prefix = prefix;
            this.dateFormat = dateFormat;
            this.date = date;
            this.tableName = tableName;
            this.corpId = corpId;
        }

        public String getPrefix() {
            return prefix;
        }

        public void setPrefix(String prefix) {
            this.prefix = prefix;
        }

        public String getDateFormat() {
            return dateFormat;
        }

        public void setDateFormat(String dateFormat) {
            this.dateFormat = dateFormat;
        }

        public String getDate() {
            return date;
        }

        public void setDate(String date) {
            this.date = date;
        }

        public String getTableName() {
            return tableName;
        }

        public void setTableName(String tableName) {
            this.tableName = tableName;
        }

        public String getCorpId() {
            return corpId;
        }

        public void setCorpId(String corpId) {
            this.corpId = corpId;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) {
                return true;
            }
            if (o == null || getClass() != o.getClass()) {
                return false;
            }
            SysGenNoteRolePK that = (SysGenNoteRolePK) o;
            return Objects.equals(prefix, that.prefix) &&
                    Objects.equals(dateFormat, that.dateFormat) &&
                    Objects.equals(date, that.date) &&
                    Objects.equals(tableName, that.tableName) &&
                    Objects.equals(corpId, that.corpId);
        }

        @Override
        public int hashCode() {
            return Objects.hash(prefix, dateFormat, date, tableName, corpId);
        }
    }
}
