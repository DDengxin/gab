function getByBrand() {
    try {
        var cgStype = hmq.getQueryString("cgStype");
        var cgName = hmq.getQueryString("cgName");
        form.getField("orient").setValue(hmq.getQueryString("orient"));
        if (hmq.isNotEmpty(cgStype)) {
            form.getField("cgStype").setValue(cgStype);
            form.getField("cgName").setValue(cgName);
            setIdReadOnly("cgStype,cgName");
            $.get("/system/parameter/getByAddName/{0}".format(cgStype || ''), function (res) {
                if (200 === res.code) {
                    var data = mini.decode(res.data);
                    srch_bottomgrid.updateColumn("cpcodeXp", {header: data.cpcodeXp});
                    srch_bottomgrid.updateColumn("cpcodeSizeEn", {header: data.cpcodeSizeEn});
                    srch_bottomgrid.updateColumn("cpcodeName", {header: data.cpcodeName});
                    srch_bottomgrid.updateColumn("cpcodeSize", {header: data.cpcodeSize});
                    srch_bottomgrid.updateColumn("cpcodeFl", {header: data.cpcodeFl});
                }
            });
        }
    } catch (e) {
		('undefined' !== typeof(console)) && console.error(e)
    }

}